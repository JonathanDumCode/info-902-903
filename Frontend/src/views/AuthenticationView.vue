<script setup lang="ts">
import { useUserStore } from '@/stores/user-store/user-store'
import { ref } from 'vue'

const userStore = useUserStore()

const pseudo = ref('')
const password = ref('')

function register() {
  if (pseudo.value && password.value) {
    console.log('Pseudo:', pseudo.value)
    console.log('Password', password.value)
    userStore.register(pseudo.value, password.value)
  }
}
</script>

<template>
  <div class="authentication">
    <v-card class="mx-auto my-auto px-6 py-8 w-100" max-width="344">
      <v-text-field
        v-model="pseudo"
        label="Pseudo"
        variant="outlined"
        color="success"
        type="text"
      ></v-text-field>
      <v-text-field
        v-model="password"
        label="Password"
        variant="outlined"
        color="success"
        type="password"
      ></v-text-field>
      <v-btn @click="register" color="success" :loading="userStore.getIsLoading"
        >S'enregistrer</v-btn
      >
    </v-card>
  </div>
</template>

<style scoped>
.authentication {
  display: flex;
  height: 100vh;
}
</style>
