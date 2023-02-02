<template>
  
  <v-main >
<HeaderPage/>

      <v-container >
        <v-row>
          <v-col
            v-for="n in 4"
            :key="n"
            cols="4"
          >
 <ProductCard/>
          </v-col>
        </v-row>
      </v-container>

  </v-main>
  <!-- footer -->
  <v-footer padless color="teal accent-1">
    <v-col
      class="text-center"
      cols="12"
    >
      {{ new Date().getFullYear() }} — <strong>SeaSell</strong>
    </v-col>
  </v-footer>
</template>

<script>

import userService from '@/services/user.service';
import HeaderPage from '@/components/HeaderPage.vue';
import ProductCard from '@/components/ProductCard.vue';

  export default {
    //  start of slideshow
    data() {
        return {

                user: {
                  initials: '',
        fullName: '',
        email: '',
      },
            items: [
                {
                    src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
                },
                {
                    src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
                },
                {
                    src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
                },
                {
                    src: "https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg",
                },
            ],
        };
    }, 

    components: { HeaderPage,ProductCard},    mounted(){
        userService.getCurrentClientUser().then((resp)=>{
            this.user.FullName = resp.data.preferredUserName;
            this.user.email=resp.data.email;
            this.user.initials=resp.data.preferredUserName[0];
        })
    },
}
</script>