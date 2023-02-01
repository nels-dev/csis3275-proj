<template>
  
  <v-main >
    <v-app-bar
      app
      shrink-on-scroll
    >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title class="color:teal lighten-4">SeaSell</v-toolbar-title>
      
      <v-img
      src="../assets/seasell_logo.svg"
      height="200px"
    ></v-img>

      <v-spacer></v-spacer>
      <ClientName/>
      <v-btn icon>

        <!-- start of avatar -->
        <v-container
      fluid
      style="height: 300px"
    >
      <v-row justify="center">
        <v-menu
          bottom
          min-width="200px"
          rounded
          offset-y
        >
          <template v-slot:activator="{ on }">
            <v-btn
              icon
              x-large
              v-on="on"
            >
              <v-avatar
                color="brown"
                size="48"
              >
              <span class="white--text text-h5">IW</span>
                <!-- <span class="white--text text-h5">{{ user.initials }}</span> -->
              </v-avatar>
            </v-btn>
          </template>
          <v-card>
            <v-list-item-content class="justify-center">
              <div class="mx-auto text-center">
                <v-avatar
                  color="brown"
                >
                  <span class="white--text text-h5">IW</span>
                  <!-- <span class="white--text text-h5">{{ user.initials }}</span> -->
                </v-avatar>
                <h3>Ivan Wong</h3>
                <!-- <h3>{{ user.fullName }}</h3> -->
                <p class="text-caption mt-1">
                  ivanwong@professor.douglascollege.ca
                  <!-- {{ user.email }} -->
                </p>
                <v-divider class="my-3"></v-divider>
                <v-btn
                  depressed
                  rounded
                  text
                >
                  My order
                </v-btn>
                <v-divider class="my-3"></v-divider>
                <v-btn
                  depressed
                  rounded
                  text
                >
                  My store
                </v-btn>
              </div>
            </v-list-item-content>
          </v-card>
        </v-menu>
      </v-row>
    </v-container>
        <!-- end of avatar -->
      </v-btn>
    </v-app-bar>

    <v-main>
        <!-- start of the slideshow -->
        <v-carousel :show-arrows="false">
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      cover
    ></v-carousel-item>
  </v-carousel>
        <!-- end of the slideshow -->
      <v-container >
        <v-row>
          <v-col
            v-for="n in 4"
            :key="n"
            cols="4"
          >
          <!-- start of the card -->
          
          <v-card
    class="mx-auto"
    max-width="344"
  >
    <v-img
      src='https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg'
      height="200px"
    ></v-img>

    <v-card-title>
     Snowboard
    </v-card-title>
    <v-card-title>
     $359.99
    </v-card-title>
    <v-card-subtitle>
      Size: 130cm for riders 80-150lbs
    </v-card-subtitle>

    <v-card-actions >

    <v-btn
      depressed
      color="success"
    >
      Purchase
    </v-btn>


    


      <v-spacer></v-spacer>
      


    </v-card-actions>

  </v-card>
            <!-- end of the card -->
          </v-col>
        </v-row>
      </v-container>
    </v-main>
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
import ClientName from '@/components/ClientName.vue';


  export default {
    //  start of slideshow
    data() {
        return {
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
    components: { ClientName }
}
</script>