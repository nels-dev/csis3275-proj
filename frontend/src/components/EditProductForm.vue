<template>
  <v-container>
    <h1>Edit store item</h1>
    <form @submit.prevent="submit" class="my-10">
      Product name:
      <v-text-field
        v-model="form.name"
        :error-messages="getMessage('name')"
      ></v-text-field>
      Detailed product description:
      <v-text-field
        v-model="form.description"
        :error-messages="getMessage('description')"
      ></v-text-field>
      Price:
      <v-text-field
        v-model="form.price"
        :error-messages="getMessage('price')"
      ></v-text-field>
      Condition:
      <v-text-field
        v-model="form.condition"
        :error-messages="getMessage('condition')"
      ></v-text-field>
      Delivery method:
      <v-select
        label="Select from below"
        :items="['Standard shipment', 'Self Pick-up']"
        v-model="form.deliveryMode"
        :error-messages="getMessage('deliveryMode')"
      ></v-select>
      Product image(s):
      <v-file-input label="Upload product images"></v-file-input>
      <v-btn color="primary" class="mr-2 my-2" type="submit">Save</v-btn>
      <v-btn
        @click="$router.push('/client/mystore')"
        variant="text"
        class="my-2"
        >Return</v-btn
      >
    </form> </v-container
  >>
</template>
<script>
import productService from "@/services/product.service";
import storeService from "@/services/store.service";
import { useVuelidate } from "@vuelidate/core";
import { required, numeric } from "@vuelidate/validators";

export default {
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
          this.$router.push("/client/mystore");
          this.$store.dispatch("alert/pushInfo", "Product edited");
        })
        .catch((error) => {
          if (error.response?.status == 400) {
            console.log(error);
            this.error = error.response.data;
          }
        });
    },
    getMessage(field) {
      return this.vuelidate.form[field].$errors.map((e) => e.$message);
    },
  },
};
</script>
