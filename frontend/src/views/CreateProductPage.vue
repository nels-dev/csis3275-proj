<template>
  <v-main>
    <HeaderPage />
  </v-main>

  <v-main>
    <v-sheet width="1000" class="mx-auto">
      <v-form>
        <v-text-field v-model="name" label="Product name:"></v-text-field>

        <v-text-field
          v-model="description"
          label="Detailed product description:"
        ></v-text-field>
        <v-text-field
          v-model.number="price"
          label="Price:"
          type="number"
          step="0.01"
        ></v-text-field>
        <v-text-field v-model="condition" label="Condition"></v-text-field>
        <v-select
          label="Delivery method"
          :items="['Standard shipment', 'Self Pick-up']"
        ></v-select>
        <v-file-input
          v-model="filePath"
          ref="file"
          multiple
          label="Upload product images"
          accept="image/*"
          @change="uploadImage($event.target.files)"
        ></v-file-input>
        <v-btn color="primary " @click="submitProduct"> Create </v-btn>
        <v-btn @click="$router.push('/Home')" variant="text">Return</v-btn>
      </v-form>
    </v-sheet>
  </v-main>
  <v-main>
    <FooterPage />
  </v-main>
</template>
<script>
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import storeService from "@/services/store.service";
export default {
  data() {
    return {
      name: "",
      description: "",
      price: null,
      condition: "",
      filePath: null,
      currentFile: undefined,
    };
  },
  methods: {
    uploadImage(file) {
      //const file = new File([this.filePath[0]], this.filePath[0].name);
      this.currentFile = file[0];
      console.log(this.currentFile);
    },
    submitProduct() {
      const productParams = new FormData();
      productParams.append("name", this.name);
      productParams.append("description", this.description);
      productParams.append("price", this.price);
      productParams.append("condition", this.condition);
      console.log(this.currentFile);
      productParams.append("images", this.currentFile);
      // for (let i = 0; i < this.filePath.length; i++) {
      //   productParams.append("images", this.filePath[i]);
      // }
      console.log(productParams);
      storeService.createProduct(productParams).then((response) => {
        console.log("creating product...", response);
      });
    },
  },
  components: { HeaderPage, FooterPage },
};
</script>
