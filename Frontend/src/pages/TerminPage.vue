<template>
	<q-page class="termin-page">
		<div class="form-container">
			<!-- Header with title and description inputs -->
			<div class="form-header">
				<q-input
					filled
					v-model="termin.titel"
					:label="language['titel'][accountStore.sprache]"
					placeholder="Geben Sie den Titel ein" />
				<q-input
					filled
					v-model="termin.beschreibung"
					:label="language['beschreibung'][accountStore.sprache]"
					type="textarea"
					autogrow
					placeholder="Beschreibung des Termins" />
			</div>

			<!-- Row with month view and toggles -->
			<div class="row-container">
				<div class="month-view">
					<div class="date-label">
						{{ language["startzeit"][accountStore.sprache] }}
					</div>
					<q-date
						v-model="termin.startDate"
						mask="YYYY-MM-DD"
						default-view="Calendar"
						class="small-calendar" />

					<q-input
						v-model="termin.startTime"
						type="time"
						class="time-picker"
						v-if="!fullDay && hasEnd" />
				</div>
				<div class="month-view" v-if="hasEnd">
					<div class="date-label">
						{{ language["endzeit"][accountStore.sprache] }}
					</div>
					<q-date
						v-model="termin.endDate"
						mask="YYYY-MM-DD"
						default-view="Calendar"
						class="small-calendar" />
					<q-input
						v-model="termin.endTime"
						type="time"
						class="time-picker"
						v-if="!fullDay && hasEnd" />
				</div>
				<div class="time-container column" v-if="!fullDay && !hasEnd">
					<q-input
						v-model="termin.startTime"
						type="time"
						:hint="language['startzeit'][accountStore.sprache]"
						class="time-picker" />
					<q-input
						v-model="termin.endTime"
						type="time"
						:hint="language['endzeit'][accountStore.sprache]"
						class="time-picker" />
				</div>
				<div class="toggle-container">
					<q-toggle v-model="fullDay" label="Ganztägig" />
					<q-toggle v-model="hasEnd" label="Mehrere Tage" />
				</div>
			</div>

			<!-- Action button -->
			<div class="form-actions">
				<q-btn
					:label="
						language['erstellen'][
							accountStore.account.einstellungen?.sprache
						]
					"
					color="primary"
					@click="createTermin" />
			</div>
		</div>
	</q-page>
</template>

<script setup>
import { ref, watch } from "vue";
import { useKalendarStore } from "src/stores/kalendar";
import { useAccountStore } from "src/stores/account";
import language from "src/language";
import { useRouter } from "vue-router";

const router = useRouter();

const accountStore = useAccountStore();
const kalendarStore = useKalendarStore();

// Termin model with fields for title, description, start/end dates and times.
const termin = ref({
	titel: "",
	beschreibung: "",
	startDate: "",
	startTime: "",
	endDate: "",
	endTime: "",
});

// Toggles for full day event and for showing end date/time.
const fullDay = ref(false);
const hasEnd = ref(false);

// If fullDay is toggled on, auto-set the times and hide time pickers.
watch(fullDay, (newVal) => {
	if (newVal) {
		// For a full day event, no time selection is needed.
		// Set start to midnight and end to one millisecond before midnight.
		if (termin.value.startDate) {
			termin.value.startTime = "";
		}
		if (termin.value.endDate) {
			termin.value.endTime = "";
		}
	} else {
		// Clear times when turning off full day so user can input times.
		termin.value.startTime = "";
		termin.value.endTime = "";
	}
});

// Auto-set end date/time to one hour after start if start is set
watch(
	[() => termin.value.startDate, () => termin.value.startTime],
	([newDate, newTime]) => {
		if (newDate && newTime && !fullDay.value && !hasEnd.value) {
			const dt = new Date(newDate + "T" + newTime);
			dt.setHours(dt.getHours() + 1);
			termin.value.endDate = dt.toISOString().split("T")[0];
			termin.value.endTime = dt
				.toTimeString()
				.split(" ")[0]
				.substring(0, 5);
		}
	}
);

const get_formatted_date = (dateString) => {
	const date = new Date(dateString);

	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are zero-based
	const day = String(date.getDate()).padStart(2, "0");
	return `${year}-${month}-${day}`;
};

const createTermin = async () => {
	if (fullDay.value) {
		if (!hasEnd.value) {
			// Single-day full day event.
			termin.value.startTime = "";
			termin.value.endTime = "";
			termin.value.endDate = termin.value.startDate;
		} else {
			// Multi-day full day event: user sets separate start and end dates.
			termin.value.startTime = "";
			termin.value.endTime = "";
		}
	}

	const startzeit = `${termin.value.startDate} ${termin.value.startTime}`;
	const endzeit = `${termin.value.endDate} ${termin.value.endTime}`;
	try {
		const temp = {
			titel: termin.value.titel,
			beschreibung: termin.value.beschreibung,
			startzeit:
				get_formatted_date(termin.value.startDate) +
				" " +
				termin.value.startTime,
			endzeit:
				get_formatted_date(termin.value.endDate) +
				" " +
				termin.value.endTime,
		};
		console.log(temp);
		await kalendarStore.addTermin(temp);
		router.push("/calendar");
		// Clear form
		termin.value = {
			titel: "",
			beschreibung: "",
			startDate: "",
			startTime: "",
			endDate: "",
			endTime: "",
		};
		fullDay.value = false;
		hasEnd.value = false;
	} catch (error) {
		console.error("Error creating termin:", error);
	}
};
</script>

<style lang="scss" scoped>
.termin-page {
	height: 100vh; /* Use full viewport height */
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 0;
	margin: 0;
}

.form-container {
	width: 70%;
	height: 90%;
	display: flex;
	flex-direction: column;
	gap: 16px;
}

.form-header {
	display: flex;
	flex-direction: column;
	gap: 8px;
}

.row-container {
	display: flex;
	align-items: center;
	justify-content: space-between;
	gap: 16px;
}

.month-view {
}

.small-calendar {
	margin: auto;
}

.toggle-container {
	display: flex;
	flex-direction: column;
	gap: 8px;
}

.time-container {
	display: flex;
	justify-content: left;
}

.time-picker {
	width: 200px;
	margin: auto;
}

.date-label {
	font-size: 1.2rem;
	font-weight: bold;
	text-align: center;
}

.date-container {
	display: grid;
	gap: 16px;
	grid-template-columns: 1fr; /* only one column by default */
}

.date-column {
	display: flex;
	flex-direction: column;
	gap: 8px;
	flex: 1;
	align-items: center;
}

.full-calendar {
	/* Set the calendar to fill the column */
	width: auto;
	margin: auto;
	height: 100%;
}

.form-actions {
	display: flex;
	justify-content: flex-end;
}

.toggle-row {
	display: none; /* Not needed since toggles are now in .toggle-container */
}
</style>
