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

const aufgaben = aufgabenStore.aufgaben;

const calendarApp = createCalendar({
	selectedDate: "2023-12-19",
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
			start: "2023-12-19",
			end: "2023-12-19",
		},
		{
			id: 2,
			title: "Event 2",
			start: "2023-12-20 12:00",
			end: "2023-12-20 13:00",
		},
	],
});
</script>
