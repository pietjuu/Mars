import { createRouter, createWebHistory } from 'vue-router';

const CONFIG = require("@/config.json");

import DashboardView from "@/views/DashboardView";
import SendItemView from "@/views/SendItemView";
import CalculatePriceView from "@/views/CalculatePriceView";
import DestinationsView from "@/views/DestinationsView";
import HistoryView from "@/views/HistoryView";
import BlacklistView from "@/views/BlacklistView";

const routes = [
  {
    path: `/`,
    alias: `/${CONFIG.group}/`,
    redirect: `/${CONFIG.group}/dashboard`
  },
  {
    path: `/${CONFIG.group}/dashboard`,
    name: 'Dashboard',
    component: DashboardView
  },
  {
    path: `/${CONFIG.group}/send-item`,
    name: 'Send Item',
    component: SendItemView
  },
  {
    path: `/${CONFIG.group}/calculate-price`,
    name: 'Calculate Price',
    component: CalculatePriceView
  },
  {
    path: `/${CONFIG.group}/destinations`,
    name: 'Destinations',
    component: DestinationsView
  },
  {
    path: `/${CONFIG.group}/statistics`,
    name: 'Statistics',
    component: DestinationsView
  },
  {
    path: `/${CONFIG.group}/history`,
    name: 'History',
    component: HistoryView
  },
  {
    path: `/${CONFIG.group}/blacklist`,
    name: 'Blacklist',
    component: BlacklistView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
