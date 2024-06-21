<script>
import {onMounted, ref} from 'vue'

export default {
    data() {
        return {
            drawer: false,
            showComponent: true
        }
    },
    beforeRouteLeave(to, from, next) {
        this.drawer = false
        next()
    },
    beforeUpdate() {
        this.drawer = false
    }
}

onMounted(() => {
  console.log("success");
});

</script>

<template>
    <v-layout class="rounded rounded-md">
        <v-navigation-drawer app v-model="drawer">
            <v-list nav>
                <v-list-item title="首页" value="index" class="text-center" @click="() => {this.$router.push('/index')}"></v-list-item>
                <v-list-item title="数据展示" value="home" class="text-center" @click="() => {this.$router.push('/home')}"></v-list-item>
                <!--
                <v-list-item title="更多数据展示" value="more" class="text-center" @click="() => {this.$router.push('/more')}"></v-list-item>
                -->
                <v-list-item title="其他信息" value="fqa" class="text-center" @click="() => {this.$router.push('/fqa')}"></v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar app>
            <v-btn @click="() => {this.drawer = !this.drawer}">
                <v-img src="@/picture/side_sign.jpg" max-height="30px">menu</v-img>
            </v-btn>
            <v-spacer></v-spacer>
            <v-toolbar-title>
                航班信息数据系统
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar>

        <v-main>
            <v-container class="fill-height" fluid>
                <v-row align="center" justify="center">
                    <v-expand-x-transition name="fade">
                        <router-view v-if="showComponent"></router-view>
                    </v-expand-x-transition>
                </v-row>
            </v-container>
        </v-main>
    </v-layout>
</template>

<style scoped>
.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to {
    opacity: 0;
}
</style>
