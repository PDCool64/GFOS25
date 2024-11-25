<template>
	<div>
		<h1 class="title">Chat</h1>
	</div>

	<div class="q-pa-md row justify-center">
		<div style="width: 100%; max-width: 700px">
			<q-chat-message
				name="me"
				avatar="https://cdn.quasar.dev/img/avatar1.jpg"
				:text="['Hi, wie gehts? :)']"
				stamp="7 minutes ago"
				sent
				bg-color="accent" />
			<q-chat-message
				name="Jane"
				avatar="https://cdn.quasar.dev/img/avatar5.jpg"
				:text="[
					'Liebe Umwelt-AG, anlässlich des Umstandes, dass nur eine verminderte Anzahl an Personen zu dem vereinbarten Termin Zeit haben, dachten wir, dass ein Wagnis bezüglich einer zeitlichen Umstrukturierung gewünscht und vorteilhaft wäre. Daher würden wir gerne keinen festen Termin einplanen, sondern geplante und abgesprochene Termine, an denen jeder, welcher so freundlich ist das Vorhaben zu unterstützen oder ein Großteil dieser, die Möglichkeit hat, in einem für ihn praktikablen Ausmaß, zu erscheinen, über dieses Team individuellerer zu be-/abzusprechen. Ich danke jedem, der seine Zeit unseren Vorhaben zu Verfügung stellt.',
				]"
				stamp="4 minutes ago"
				text-color="white"
				bg-color="secondary" />
			<q-chat-message
				name="me"
				avatar="https://cdn.quasar.dev/img/avatar1.jpg"
				:text="['I am good, thanks for asking.']"
				stamp="2 minutes ago"
				sent
				bg-color="accent" />
		</div>
	</div>
</template>

<script>
import { computed, ref } from "vue";
import { getMessageBy } from "src/requests/aufgabe";
</script>

<style scoped>
.title {
	font-size: 5em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
	margin-left: 50px;
}
</style>
