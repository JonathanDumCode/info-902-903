import router from '@/router'
import { defineStore } from 'pinia'
import type { User } from './models/User'
import { UserService } from './service/user.service'

interface State {
  user: User | null
  isLoading: boolean
}

export const useUserStore = defineStore('useUserStore', {
  state: (): State => ({
    user: null,
    isLoading: false
  }),
  getters: {
    getUser: (state) => state.user,
    getIsLoading: (state) => state.isLoading
  },
  actions: {
    async register(pseudo: string, password: string) {
      this.isLoading = true
      const user = await UserService.register(pseudo, password)
      this.user = user
      this.isLoading = false
      if (user) {
        router.push('/dashboard')
      }
    },

    async login(pseudo: string, password: string) {
      this.isLoading = true
      const user = await UserService.login(pseudo, password)
      this.user = user
      this.isLoading = false
      if (user) {
        router.push('/dashboard')
      }
    }
  }
})
