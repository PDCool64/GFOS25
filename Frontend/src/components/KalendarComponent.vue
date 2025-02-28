<template>
	<div>
		<ScheduleXCalendar :calendar-app="calendarApp">
			<template #eventModal="{ calendarEvent }">
				<q-card>
					<q-card-section>
						<div class="text-h6">{{ calendarEvent.title }}</div>
						<div class="text-subtitle">
							{{ calendarEvent.description }}
						</div>
						<q-separator />
						<div class="text-subtitle">Aufgaben:</div>
						<div
							class="text-subtitle2"
							v-for="aufgabe in calendarEvent.aufgaben"
							:key="aufgabe">
							{{ aufgabe.titel }}
						</div>
						<q-separator />
						<div class="text-subtitle">Teilnehmende:</div>
						<div
							class="text-subtitle2"
							v-for="teilnehmer in calendarEvent.teilnehmer"
							:key="teilnehmer">
							{{ teilnehmer.vorname }}
							{{ teilnehmer.nachname }}
						</div>
					</q-card-section>
				</q-card>
			</template>
		</ScheduleXCalendar>
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
import { useQuasar } from "quasar";

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
const $q = useQuasar();

const events = ref([]);

console.log(events.value);

const eventsService = createEventsServicePlugin();

const calendarApp = createCalendar({
	selectedDate: get_formatted_date(new Date()),
	dayBoundaries: {
		start: "08:00",
		end: "18:00",
	},
	isDark: $q.dark.isActive,
	views: [
		createViewDay(),
		createViewWeek(),
		createViewMonthGrid(),
		createViewMonthAgenda(),
	],
	plugins: [createEventModalPlugin(), eventsService],
	events: events.value,
});

kalenderStore.fetchOwnTermine().then(() => {
	console.log(kalenderStore.termine);
	for (let id in kalenderStore.termine) {
		const temp = kalenderStore.termine[id];
		let termin = {};
		termin.start = temp.startzeit;
		termin.end = temp.endzeit;
		termin.title = temp.titel;
		termin.description = temp.beschreibung;
		termin.description += "\n\n";
		termin.aufgaben = [];
		termin.teilnehmer = [];
		console.log(temp);
		for (const behandlung of temp.aufgabenbehandlungList) {
			termin.aufgaben.push(behandlung.aufgabe);
		}
		for (const kalendar of temp.kalendarList) {
			termin.teilnehmer.push(kalendar.account);
		}
		console.log(termin);
		events.value.push(termin);
	}
	console.log(kalenderStore.termine);
	eventsService.set(events.value);
});
</script>

<style>
.text-subtitle {
	font-weight: bold;
	margin-top: 1vh;
}

.text-subtitle2 {
	color: var(--q-primary);
}
</style>
