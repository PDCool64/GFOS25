<!-- Diesen File bitte noch einmal überarbeiten tun, weil sonst ist der schlimmer als wie du! -->

<template>
    <div
        class="fullscreen bg-blue text-white text-center q-pa-md flex flex-center"
    >
        <div>
            <div style="font-size: 30vh">404</div>

            <div class="text-h2" style="opacity: 0.4">
                Dieser Text ist Eigentum der Bundesrepublik Deutschland

                <h2>Wer da ist, ist ein Hurensohn - Kant, 2022</h2>
            </div>
            <div class="du-hurensohn">SPRICH DEUTSCH</div>
            <q-btn
                class="q-mt-xl"
                color="white"
                text-color="blue"
                unelevated
                to="/"
                label="Go Home"
                no-caps
            />
        </div>
    </div>
</template>

<script setup>
defineOptions({
    name: 'ErrorNotFound',
});
</script>

<style scoped>
.du-hurensohn {
    color: white;
    font-size: 2rem;
    margin-top: 1rem;
    opacity: 0.5;
}
</style>
