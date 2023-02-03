<template>

  <v-main >
<HeaderPage/>
  </v-main>

<v-main>
<ProductForm/>
  </v-main>


<v-main>

  <FooterPage/>
</v-main>

  </template>
  <script>
  import HeaderPage from '@/components/HeaderPage.vue';
  import FooterPage from '@/components/FooterPage.vue';
    import ProductForm from '@/components/ProductForm.vue';
    export default {
      //
          components: { HeaderPage,FooterPage,ProductForm}
    }
  </script>