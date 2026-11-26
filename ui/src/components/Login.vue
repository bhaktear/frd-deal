<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
import axios from 'axios'
import router from '../router'

const formFields = ref([])
const formData = reactive({})
const submitForm = async () => {
    const fd = new FormData()
    Object.entries(formData).forEach(([key, value]) => {
        fd.append(key, value ?? '')
    })
    try {
        const resp = await axios.post('/api/auth/login', fd)
        if(resp.data.err == 1){
            alert(resp.data.msg);
            return false;
        }
        sessionStorage.setItem('loginInfo', JSON.stringify(resp.data.data))
        await router.push('/dashboard')
        return true
    } catch (error) {
        alert("Error submitting form: " + error.message)
    }
    
}

</script>
<template>
    <div class="d-flex justify-content-center">
        <div class="login-box mb-4 mt-4">
            <div class="card shadow">
                <div class="card-header bg-primary">Sign In</div>
                <div class="card-body login-card-body">
                    <form @submit.prevent="submitForm">
                        <div class="input-group mb-3">
                            <input type="text" v-model="formData.userId" id="userId" class="form-control" placeholder="User Name" required>
                            <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                        </div>
                        <div class="input-group mb-3">
                            <input type="password" v-model="formData.password" id="password" class="form-control" placeholder="Password" required>
                            <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-block">Sign In</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>