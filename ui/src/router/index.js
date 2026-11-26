import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '../components/Login.vue';
import DashboardView from '../components/Dashboard.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView,
      meta: { layout: 'blank', requiresAuth: false }
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView,
      meta: { requiresAuth: true },
      children: [
        {
          path: 'deal/add', name : 'add-deal', component: () => import('../components/DealAddView.vue')
        }
      ]
    },
  ]
});

// Redirects unauthenticated users back to '/'
router.beforeEach((to) => {
  const loginInfo = sessionStorage.getItem('loginInfo');
  let isLoggedIn = false
  if(loginInfo){
    try{
      const parsed = JSON.parse(loginInfo);
      isLoggedIn = Boolean(parsed && Object.keys(parsed).length > 0);
    }catch{
      isLoggedIn = false;
    }
  }
  if (to.meta.requiresAuth && !isLoggedIn) {
    return { name: 'login' };
  }

  if (to.name === 'login' && isLoggedIn) {
    return { name: 'dashboard' };
  }

});

export default router;
