<template>
	<div class="q-pa-md column justify-center">
		<div
			style="margin-bottom: 20%; height: 80vh; overflow-y: scroll"
			id="chat">
			<div
				style="width: 70%"
				v-for="(message, index) in messages"
				:key="index">
				<q-chat-message
					:text="message.content"
					:sent="message.sent"
					:name="
						message.sender.vorname + ' ' + message.sender.nachname
					"
					:stamp="get_display_time(message.timeSent)" />
			</div>
		</div>
		<div class="input row" style="background: white; padding-bottom: 5px">
			<q-input
				v-model="message"
				@keyup.enter="send_message"
				placeholder="Nachricht eingeben"
				dense
				class="col-11" />
			<q-btn icon="send" round flat @click="send_message" />
		</div>
	</div>
</template>

<script setup>
import { get_no_data, post } from "src/request";
import { useAccountStore } from "src/stores/account";
import { useMessageStore } from "src/stores/message";
import { ref } from "vue";

const props = defineProps({
	receiver: String,
});

const accountStore = useAccountStore();
const messageStore = useMessageStore();

const messages = ref([]);

const get_display_time = (time) => {
	const date = new Date(time);
	const hours = date.getHours().toString().padStart(2, "0");
	const minutes = date.getMinutes().toString().padStart(2, "0");
	return hours + ":" + minutes;
};

const scroll_to_bottom = () => {
	const chat = document.getElementById("chat");
	chat.scrollTop = chat.scrollHeight;
};

const message = ref("");

const send_message = async () => {
	const response = await post("/messages", {
		sender: accountStore.account.id.toString(),
		receiver: props.receiver,
		content: message.value,
		timeSent: new Date().toISOString(),
		isReceived: false,
		isRead: false,
	});
	console.log(response);
	if (response.ok) {
		message.value = "";
		get_messages().finally(() => {
			scroll_to_bottom();
		});
		scroll_to_bottom();
	}

	// get_messages();

	const data = await response.json();
};

console.log(props.receiver);
console.log(accountStore.account.id);

const get_messages = async () => {
	const response = await get_no_data(
		"/messages/chat/" + accountStore.account.id + "/" + props.receiver
	);
	const data = await response.json();
	console.log(data);
	const sent = data.sent;
	const received = data.received;
	process_messages(sent, received);
};

const process_messages = (sent, received) => {
	for (let i = 0; i < sent.length; i++) {
		sent[i].sent = true;
		sent[i].content = [sent[i].content];
	}
	for (let i = 0; i < received.length; i++) {
		received[i].sent = false;
		received[i].content = [received[i].content];
	}
	// merge sent and received messages
	messages.value = sent.concat(received);
	// sort messages by timestamp
	messages.value.sort((a, b) =>
		new Date(a.timeSent) > new Date(b.timeSent) ? 1 : -1
	);
	// merge messages that are sent within 5 minutes of each other
	for (let i = 0; i < messages.value.length - 1; i++) {
		const time1 = new Date(messages.value[i].timeSent);
		const time2 = new Date(messages.value[i + 1].timeSent);
		const diff = time2 - time1;
		if (
			messages.value[i].sent === messages.value[i + 1].sent &&
			diff < 300000
		) {
			messages.value[i].content = messages.value[i].content.concat(
				messages.value[i + 1].content
			);
			console.log(messages.value[i].content);
			messages.value.splice(i + 1, 1);
			i--;
		}
	}
};

get_messages().finally(() => {
	scroll_to_bottom();
});
</script>

<style lang="scss" scoped>
.input {
	/* make input fixed to the bottom with 10% margin */
	position: fixed;
	width: 80%;
	bottom: 30px;
	text-align: center;
}
</style>
