import { createRouter, createWebHistory } from 'vue-router'
import ChargeLayout from './vue/ChargeLayout.vue'
import Home from './vue/Home.vue'
import Find from './vue/Find.vue'
import Indexs from './vue/Indexs.vue'
import Fqa from './vue/INFO.vue'

const routes = [
  {
    path: '/',
    mode: 'history',
    component: ChargeLayout,
    children : [
      {path: '', component: Indexs},
      {path: 'index', component: Indexs},
      {path: 'home', component: () => import('./vue/Home.vue')},
      {path: 'finduser', component: Find},
      {path: 'more', component: () => import('./vue/MoreEcharts.vue')},
      {path: 'fqa', component: Fqa},
      /*
      {path: 'findaver', component: FindAver},
      {path: 'findcharge', component: FindChargeAver},
      {path: 'aver', component: Aver}

       */
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router