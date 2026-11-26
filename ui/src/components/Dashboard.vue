<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'
import router from '../router'
import { RouterLink  } from 'vue-router'


const loginInfo = JSON.parse(sessionStorage.getItem('loginInfo'))
const login_id = loginInfo.login_id
const login_details = loginInfo.user_name

const menu = ref({})
const smenu = ref({})
const isMenuLoading = ref(false)
const expandedMenus = ref([])

const toggleMenu = (code) => {
    const index = expandedMenus.value.indexOf(code)

    if (index !== -1) {
        expandedMenus.value.splice(index, 1)
    } else {
        expandedMenus.value.push(code)
    }
}

const isExpanded = (code) => {
    return expandedMenus.value.includes(code)
}

const getMenu = async() => {
    isMenuLoading.value = true
    try{
        const resp = await axios.get('/api/getMenu', {withCredentials: true,})
        const data = resp.data
        if(data.err == 2){
            await forceLogoutOnExpiredSession(data.msg)
            return 
        }
        if(data.err == 1){
            alert(data.msg)
            return
        }
        menu.value = data.data.menu || {}
        smenu.value = data.data.smenu || {}
        expandedMenus.value = []
    }catch(error){
        alert("Error getting from server " + error)
        console.error('getMenu error:', error)
    }finally {
        isMenuLoading.value = false
    }
}


onMounted(getMenu)
const handleLogout = async () => {
    try {
        await axios.post('/api/auth/logout', {}, { withCredentials: true })
    } catch (error) {
        console.warn('Logout request failed:', error?.message || error)
    } finally {
        sessionStorage.removeItem('loginInfo')
        await router.push({ name: 'login' })
    }
}

const forceLogoutOnExpiredSession = async (message = 'Session Expired. Please Login Again') => {
  sessionStorage.clear()
  localStorage.removeItem('loginInfo')
  localStorage.removeItem('userMenu')

  try {
    await axios.post('/api/auth/logout', {}, { withCredentials: true })
  } catch (_) {
    // Session may already be expired/invalidated on server
  }
  await router.replace({ name: 'login' })
}
</script>
<template>

    <div class="app-wrapper layout-fixed sidebar-expand-lg bg-body-tertiary">
        <nav class="app-header navbar navbar-expand bg-primary">
            <!--begin::Container-->
            <div class="container-fluid">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" data-lte-toggle="sidebar" href="#" role="button">
                        <i class="bi bi-list"></i>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item dropdown user-menu">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <img src="@/assets/login_man.png" class="user-image rounded-circle shadow" alt="User Image"/>
                            <span class="d-none d-md-inline">{{ login_id }}</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
                            <!-- User image -->
                            <li class="user-header text-bg-primary">
                                <img src="@/assets/login_man.png" class="rounded-circle shadow" alt="User Image"/>
                                <p>{{ login_details }}</p>
                            </li>
                            <!-- Menu Body -->
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <a href="#" class="btn btn-danger float-end" @click.prevent="handleLogout">Sign out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!--end::Container-->
        </nav>

        <aside class="app-sidebar bg-body-secondary shadow" data-bs-theme="dark">
            <!--begin::Sidebar Brand-->
            <div class="sidebar-brand">
                <a href="#" class="brand-link">
                    <i class="brand-image fas fa-tachometer-alt"></i>
                    <span class="brand-text fw-light">Dashboard</span>
                </a>
            </div>
            <div class="sidebar-wrapper">
                <div v-if="isMenuLoading" class="p-3 text-center text-secondary small">
                    Loading menu...
                </div>
                <nav class="mt-2">
                  <ul class="nav sidebar-menu flex-column" data-lte-toggle="treeview" role="menu" data-accordion="false">
                    <li v-for="(item, key) in menu" :key="key" class="nav-item" :class="{ 'menu-open': isExpanded(item.code) }">
                      <a href="#" class="nav-link" @click.prevent="toggleMenu(item.code)">
                        <i :class="['nav-icon', item.icon ]"></i>
                        <p>{{ item.name }}</p>
                        <i v-if="(smenu[item.code] || []).length > 0" class="nav-arrow bi bi-chevron-right"></i>
                      </a>
                      <ul v-if="(smenu[item.code] || []).length > 0" class="nav nav-treeview">
                        <li v-for="child in smenu[item.code]" :key="child.code" class="nav-item">
                          <RouterLink :to="`/dashboard/${child.url}`" class="nav-link">
                            <i class="nav-icon bi bi-circle"></i>
                            <p>{{ child.name }}</p>
                          </RouterLink>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </nav>
            </div>
        </aside>
    
        <main class="app-main">
            <div class="app-content">
              <router-view />
            </div>
        </main>
        <footer class="app-footer"></footer>
    </div>

</template>