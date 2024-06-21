<script>
import axios from "axios";

export default {
    data () {
        return {
            serverData: [],
            headers: [
                { text: '使用的充电桩ID', value: 'stationid'},
                { text: '消费金额', value: 'money'}
            ]
        };
    },
    methods: {
        async sendData() {
            console.log("sendData");
            try {
                let urlm = "http://localhost:8080/huaweiFindUserMoney?name=" + encodeURIComponent(this.user_name);
                console.log(urlm);
                await axios.get(urlm)
                    .then(response => {
                        console.log(response.data);
                        this.serverData = response.data;
                    })
                    .catch(error => {
                        console.log("error1")
                    });
            }catch (error) {
                console.error(error);
            }
        }
    }
}

</script>

<template>
    <v-col cols="12">
        <v-row>
                <v-responsive
                    class="mx-auto"
                    max-width="344"
                >
                    <v-text-field
                        v-model="user_name"
                        hint="输入想要查询的用户"
                        label="查询用户的充电信息（在此输入）"
                        type="input"
                    ></v-text-field>
                </v-responsive>
            <v-btn @click="sendData">确定</v-btn>
        </v-row>
        <v-row>
            <v-col cols="5">
                <v-card>
                    <v-card-title class="text-center">
                        使用过的充电桩ID
                    </v-card-title>
                </v-card>
            </v-col>
            <v-col cols="5">
                <v-card>
                    <v-card-title class="text-center">
                        消费金额
                    </v-card-title>
                </v-card>
            </v-col>
        </v-row>
        <v-row>
            <v-data-table
                :headers="headers"
                :items="serverData"
            >
                <template v-slot:item.stationid="{ item }">
                    <td class="text-center">{{ item.stationid }}</td>
                </template>
                <template v-slot:item.money="{ item }">
                    <td class="text-center">{{ item.money }}</td>
                </template>
            </v-data-table>
        </v-row>
    </v-col>
</template>

<style scoped>

</style>