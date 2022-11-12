import { createRouter, createWebHashHistory } from 'vue-router'

import DashboardView from "@/views/DashboardView";
import SendItemView from "@/views/SendItemView";
import CalculatePriceView from "@/views/CalculatePriceView";
import DestinationsView from "@/views/DestinationsView";
import HistoryView from "@/views/HistoryView";
import BlacklistView from "@/views/BlacklistView";

const routes = [
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: DashboardView
  },
  {
    path: '/send-item',
    name: 'Send Item',
    component: SendItemView
  },
  {
    path: '/calculate-price',
    name: 'Calculate Price',
    component: CalculatePriceView
  },
  {
    path: '/destinations',
    name: 'Destinations',
    component: DestinationsView
  },
  {
    path: '/statistics',
    name: 'Statistics',
    component: DestinationsView
  },
  {
    path: '/history',
    name: 'History',
    component: HistoryView
  },
  {
    path: '/blacklist',
    name: 'Blacklist',
    component: BlacklistView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
