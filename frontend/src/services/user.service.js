import axios from './axios'

const userService = {
    getCurrentClientUser: ()=>axios.get('/api/client/user')
}

export default userService;