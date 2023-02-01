<!-- this file is just a simple demo on how to load data from API, can be deleted -->
<template>
    Loaded from API: {{ name }}
</template>
<script>
import userService from '@/services/user.service';

export default{
    data: ()=>{
        return{
            name:""
        }
    },
    mounted(){
        userService.getCurrentClientUser().then((resp)=>{
            this.name = resp.data.preferredUserName;
        })
    }
}
</script>