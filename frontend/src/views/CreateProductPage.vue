<template>
  <v-main>
    <HeaderPage />
  </v-main>

  <v-main>
    <v-sheet width="1000" class="mx-auto">
      <v-form @submit.prevent="submitProduct">
        <v-text-field
          v-model="name"
          label="Product name:"
          :rules="[(v) => !!v || 'Name is required']"
        >
          <template v-slot:messages>
            <div v-if="!name">Please enter the product name</div>
          </template>
        </v-text-field>
      <v-form @submit.prevent="submitProduct">
        <v-text-field
          v-model="name"
          label="Product name:"
          :rules="[(v) => !!v || 'Name is required']"
        >
          <template v-slot:messages>
            <div v-if="!name">Please enter the product name</div>
          </template>
        </v-text-field>
        <v-text-field
          v-model="description"
          label="Detailed product description:"
          required
          required
        ></v-text-field>
        <v-text-field
          v-model.number="price"
          label="Price:"
          type="number"
          step="0.01"
          required
        ></v-text-field>
        <v-text-field
          v-model="condition"
          label="Condition"
          required
          required
        ></v-text-field>
        <v-text-field
          v-model="condition"
          label="Condition"
          required
        ></v-text-field>
        <v-select
          label="Delivery method"
          :items="['Standard shipment', 'Self Pick-up']"
          required
        ></v-select>
        <v-file-input
          v-model="filePath"
          ref="file"
          multiple
          required
          required
          label="Upload product images"
          accept="image/*"
          @change="uploadImage($event.target.files)"
        ></v-file-input>
        <v-btn color="primary " type="submit"> Create </v-btn>
        <v-btn color="primary " type="submit"> Create </v-btn>
        <v-btn @click="$router.push('/Home')" variant="text">Return</v-btn>
        <v-dialog v-model="dialog" max-width="500">
          <v-card>
            <v-card-title> Warning </v-card-title>
            <v-card-text>
              Please fill in all required fields before submitting.
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" text @click="dialog = false"> OK </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialog" max-width="500">
          <v-card>
            <v-card-title> Warning </v-card-title>
            <v-card-text>
              Please fill in all required fields before submitting.
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" text @click="dialog = false"> OK </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
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
      dialog: false,
      rules: [
        (value) => {
          if (value) return true;
          return "The field must not be empty";
        },
      ],
      dialog: false,
      rules: [
        (value) => {
          if (value) return true;
          return "The field must not be empty";
        },
      ],
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
      if (!this.name || !this.description || !this.price || !this.condition) {
        this.dialog = true;
        return;
      }
      if (this.price < 0) return;
      if (!this.name || !this.description || !this.price || !this.condition) {
        this.dialog = true;
        return;
      }
      if (this.price < 0) return;
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
        this.$router.push("/mystore");
        this.$router.push("/mystore");
      });
    },
  },
  components: { HeaderPage, FooterPage },
};
</script>
