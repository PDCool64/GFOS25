const routes = [
	{
		path: "/",
		component: () => import("layouts/MainLayout.vue"),
		children: [
			{
				path: "",
				component: () => import("pages/IndexPage.vue"),
				meta: { requiresAuth: false },
			},
			{
				path: "account",
				component: () => import("pages/AccountPage.vue"),
				meta: { requiresAuth: true },
			},
			{
				path: "aufgaben",
				component: () => import("components/AufgabenComponent.vue"),
				meta: { requiresAuth: true },
				children: [
					{
						path: ":id",
						name: "AufgabeDetail",
						component: () => import("pages/AufgabePage.vue"),
						meta: { requiresAuth: true },
					},
				],
			},
			{
				path: "dashboard",
				component: () => import("pages/DashboardPage.vue"),
				meta: { requiresAuth: true },
			},
			{
				path: "chat",
				component: () => import("pages/ChatPage.vue"),
				meta: { requiresAuth: true },
			},
			{
				path: "calendar",
				component: () => import("pages/KalendarPage.vue"),
				meta: { requiresAuth: true },
			},
			{
				path: "test",
				component: () => import("components/KundeComponent.vue"),
				meta: { requiresAuth: true },
			},
			{
				path: "clients",
				component: () => import("pages/KundePage.vue"),
				meta: { requiresAuth: true },
			},
		],
	},
	{
		path: "/:catchAll(.*)*",
		component: () => import("pages/ErrorNotFound.vue"),
	},
];

export default routes;
