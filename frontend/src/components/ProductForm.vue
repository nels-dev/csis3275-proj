<template>
  <v-sheet width="1000" class="mx-auto">
    <v-form disabled>
    Product name:
      <v-text-field
        value="Snowboard"
      ></v-text-field>
Detailed product description:
      <v-text-field
      value=" Perfect for Beginners: This snowboard will enter you into the world of cruising' the slopes
Durable: Hardwood construction for long lasting, repetitive use
Easy Adjusting: Hook and loop binding allows for them to be adjusted easily
Note: No metal edge.Not for resort use"
      ></v-text-field>
Price:
      <v-text-field
        value="$95.48"
      ></v-text-field>
Product image(s):
    <v-img
      src="https://m.media-amazon.com/images/I/71PeTiNaOCL._AC_SX679_.jpg"
      height="200px"
    ></v-img>
Date published:
      <v-text-field
        value="2023/01/01"
      ></v-text-field>
Condition (eg. 95% new):
      <v-text-field
        value="95% new"
      ></v-text-field>
Mode of delivery (assumed standard charge $30):
       <v-text-field
        value="Shippment"
      ></v-text-field>
Product category (with sub-categories):
      <v-text-field
        value="Sports"
      ></v-text-field>

      <v-btn
          @click="$router.push('/Home')"
      >Return</v-btn>
    </v-form>
  </v-sheet>
</template>
<script>
  export default {
    data: () => ({
      firstName: '',
      firstNameRules: [
        value => {
          if (value?.length > 3) return true

          return 'First name must be at least 3 characters.'
        },
      ],
      lastName: '123',
      lastNameRules: [
        value => {
          if (/[^0-9]/.test(value)) return true

          return 'Last name can not contain digits.'
        },
      ],
    }),
  }
</script>