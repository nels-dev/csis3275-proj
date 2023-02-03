      <template>
         <!-- start of the card -->
          <v-card
    class="mx-auto"
    max-width="344"
  >
    <v-img
      src="https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg"
      height="200px"
      cover
    @click="$router.push('/ProductDetailsPage')"
    ></v-img>

    <v-card-title
    @click="$router.push('/ProductDetailsPage')"
    >
      Snowboard
    </v-card-title>
        <v-card-title>
     $95.48
    </v-card-title>

    <v-card-subtitle>
     Size: 130cm for riders 80-150lbs
    </v-card-subtitle>
  </v-card>
            <!-- end of the card -->
</template>