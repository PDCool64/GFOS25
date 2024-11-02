const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/IndexPage.vue') },
      { path: '/test', component: () => import('pages/TestPage.vue') },
      { path: '/login', component: () => import('src/pages/LoginPage.vue') },
      { path: '/account', component: () => import('components/AccountComponent.vue') },
      { path: '/aufgaben', component: () => import('components/AufgabenComponent.vue') },
      { path: '/dashboard', component: () => import('pages/DashboardPage.vue') },
    ]
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
