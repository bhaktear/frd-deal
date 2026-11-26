import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

// 1. Bootstrap 5 CSS, Icons & JS (Must load before AdminLTE & DataTables)
import 'bootstrap/dist/css/bootstrap.min.css';
//import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

// 2. AdminLTE 4 Vue Plugin & Styles
import AdminLteVue from '@adminlte/vue';
import '@adminlte/vue/css';

// 3. DataTables Vue 3 + Bootstrap 5 Adapter
import DataTable from 'datatables.net-vue3';
import DataTablesCore from 'datatables.net-bs5';
import 'datatables.net-bs5/css/dataTables.bootstrap5.min.css';

DataTable.use(DataTablesCore);


const app = createApp(App)

app.use(AdminLteVue);
app.component('DataTable', DataTable);
app.use(router)
app.mount('#app')
