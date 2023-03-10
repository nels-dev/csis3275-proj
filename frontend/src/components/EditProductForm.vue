<template>
  <v-sheet width="1000" class="mx-auto">
    <form @submit.prevent="submit">
      Product name:
      <v-text-field v-model="form.name"></v-text-field>
      Detailed product description:
      <v-text-field v-model="form.description"></v-text-field>
      Price:
      <v-text-field v-model="form.price"></v-text-field>

      Condition:
      <v-text-field v-model="form.condition"></v-text-field>
      Delivery method:
      <v-select
        label="Select from below"
        :items="['Standard shipment (Fixed charge C$30)', 'Self Pick-up']"
        v-model="form.deliveryMode"
      ></v-select>
      Product image(s):
      <v-file-input label="Upload product images"></v-file-input>
      <v-btn color="primary" class="mr-2" type="submit">Save</v-btn>
      <v-btn @click="$router.push('/mystore')" variant="text">Return</v-btn>
    </form>
  </v-sheet>
</template>
<script>
import productService from "@/services/product.service";
import storeService from "@/services/store.service";
import { useVuelidate } from "@vuelidate/core";
import { required, numeric } from "@vuelidate/validators";

export default {
  // computed: {
  //   imageUrl() {
  //     return process.env.VUE_APP_API_URL + "/images/" + this.item.images;
  //   },
  // },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  mounted() {
    productService.getProduct(this.id).then((resp) => {
      console.log(resp.data);
      this.form = resp.data;
    });
  },
  setup() {
    return { vuelidate: useVuelidate() };
  },
  data() {
    return {
      // loading: false,
      // valid: true,
      form: {
        name: "",
        description: "",
        price: "",
        image: "",
        condition: "",
        deliveryMode: "",
      },
    };
  },
  validations: {
    form: {
      name: { required },
      description: { required },
      price: { required, numeric },
      condition: { required },
      deliveryMode: { required },
    },
  },
  methods: {
    submit() {
      this.vuelidate
        .$validate()
        .then((valid) => {
          if (valid) {
            return storeService.editProduct(this.id, this.form);
          } else {
            return Promise.reject();
          }
        })
        .then(() => {
          this.$router.push("/mystore");
          this.$store.dispatch("alert/pushInfo", "Product edited");
        })
        .catch((error) => {
          if (error.response?.status == 400) {
            console.log(error);
            this.error = error.response.data;
          }
        });
    },
  },
};
</script>
