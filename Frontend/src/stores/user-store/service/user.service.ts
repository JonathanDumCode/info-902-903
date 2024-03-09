import instance from '@/axios/axios'
import type { User } from '../models/User'

export class UserService {
  static async register(pseudo: string, password: string): Promise<User> {
    const params = {
      pseudo: pseudo,
      password: password
    }
    try {
      const response = await instance.post<User>('/user/register', params)
      return response.data
    } catch (e) {
      return Promise.reject(e)
    }
  }
}
