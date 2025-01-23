<template>
	<div>
		<ScheduleXCalendar :calendar-app="calendarApp" />
	</div>
</template>

<script setup>
import { ScheduleXCalendar } from "@schedule-x/vue";
import {
	createCalendar,
	createViewDay,
	createViewMonthAgenda,
	createViewMonthGrid,
	createViewWeek,
} from "@schedule-x/calendar";
import "@schedule-x/theme-default/dist/index.css";
import { useAufgabenStore } from "src/stores/aufgaben";

// Do not use a ref here, as the calendar instance is not reactive, and doing so might cause issues
// For updating events, use the events service plugin

const aufgabenStore = useAufgabenStore();

const get_formatted_date = (date) => {
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are zero-based
	const day = String(date.getDate()).padStart(2, "0");
	return `${year}-${month}-${day}`;
};

const aufgaben = aufgabenStore.aufgaben;

console.log(new Date("YYYY:MM:DD").toString());

const calendarApp = createCalendar({
	selectedDate: get_formatted_date(new Date()),
	dayBoundaries: {
		start: "08:00",
		end: "18:00",
	},
	views: [
		createViewDay(),
		createViewWeek(),
		createViewMonthGrid(),
		createViewMonthAgenda(),
	],
	events: [
		{
			id: 1,
			title: "Event 1",
			start: "2025-01-18",
			end: "2025-01-18",
		},
		{
			id: 2,
			title: "Event 2",
			start: "2025-01-18 12:00",
			end: "2025-01-18 13:00",
		},
	],
});
</script>
