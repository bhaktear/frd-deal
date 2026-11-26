<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
const formFields = ref([])
const formData = reactive({
    'currency': '', 'exchange_house': '','offer_rate': '', 'asked_rate': ''
})

</script>
<template>
<div id="page-wrapper" class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h4 class="page-header text-center " id="">Add Deal Information</h4>
        </div>  
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Add Deal Information</div>
                <div class="card-body">
                    <form @submit.prevent="submitForm">
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="exchange_house" class="fw-bold">Exchange House</label>
                            </div>
                            <div class="col-md-8 mb-3">
                                <select v-model="formData.exchange_house" id="currency" class="form-select" required>
                                    <option value="">Choose One</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="currency" class="fw-bold">Dealing Foreign Currency</label>
                            </div>
                            <div class="col-md-8 mb-3">
                                <select v-model="formData.currency" id="currency" class="form-select" required>
                                    <option value="">Choose One</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="offer_rate" class="fw-bold">Dealing Offiecr Offer Rate</label>
                            </div>
                            <div class="col-md-8 mb-3">
                                <input v-model="formData.offer_rate" id="offer_rate" type="number" class="form-control" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="offer_rate" class="fw-bold">Exchange House Asked Rate</label>
                            </div>
                            <div class="col-md-8 mb-3">
                                <input v-model="formData.asked_rate" id="asked_rate" type="number" class="form-control" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-center mb-3">
                                <input type="submit" id="submit" class="btn btn-info" name="submit" value="Submit">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</template>