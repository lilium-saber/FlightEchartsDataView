<script>

import image1 from '/src/picture/Designer1.png';
import image2 from '/src/picture/Designer2.png';

export default {
    data: () => ({
        loading: false,
        slides: [
            image1,
            image2
        ]
    }),

    methods: {
        load() {
            this.loading = true
            setTimeout(() => {
                this.loading = false
            }, 500)
            setTimeout(() => {
                this.turnToHome()
            }, 500)
        },

        turnToHome() {
            this.$router.push('/home')
        }
    }
}
</script>

<template >
    <v-expand-x-transition name="fade">
        <v-col cols="12" class="background">
            <v-row justify="center" style="margin-top: 50px">
                <v-carousel
                    hide-delimiters
                    :show-arrows="false"
                    height="400"
                    show-arrows="hover"
                    cycle
                    hide-delimiter-background
                    style="width: 50%; height: 50%"
                >
                    <v-carousel-item
                        v-for="(slide, i) in slides"
                        :key="i"
                    >
                        <v-img :src="slide"></v-img>
                    </v-carousel-item>
                </v-carousel>
            </v-row>
            <v-row align="center" justify="center" style="margin-top: 150px">
                <v-btn :loading="loading" variant="tonal" size="200" rounded="lg" @click="load" style="height: 100px">
                    查看数据展示
                </v-btn>
            </v-row>
        </v-col>
    </v-expand-x-transition>
</template>

<style scoped>

.background {
    background-image: url("@/picture/Designer.png");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    height: 100vh;
    width: 100%;
}
.fade-enter-active, .fade-leave-active {
    transition: opacity 1s;
}
.fade-enter, .fade-leave-to {
    opacity: 0;
}
</style>