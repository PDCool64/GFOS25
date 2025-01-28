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
import { createEventModalPlugin } from "@schedule-x/event-modal";
import { createEventsServicePlugin } from "@schedule-x/events-service";
import "@schedule-x/theme-default/dist/index.css";
import { useAufgabenStore } from "src/stores/aufgaben";
import { useKalendarStore } from "src/stores/kalendar";
import { ref } from "vue";

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
const kalenderStore = useKalendarStore();

const events = ref([]);

console.log(events.value);

const eventsService = createEventsServicePlugin();

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
	plugins: [createEventModalPlugin(), eventsService],
	events: events.value,
});

kalenderStore.fetchTermine().then(() => {
	console.log(kalenderStore.termine);
	for (let temp of kalenderStore.termine) {
		let termin = {};
		termin.start = temp.startzeit;
		termin.end = temp.endzeit;
		termin.title = temp.titel;
		termin.description = temp.beschreibung;
		console.log(termin);
		events.value.push(termin);
	}
	console.log(kalenderStore.termine);
	eventsService.set(events.value);
});
</script>
