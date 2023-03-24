<!--
    This is a wrapper for v-snackbar component to ensure consistent look-and-feel
    Intended for alert messages without a form or generic error such as conectivity issue

    Usage: through vuex store - 
    this.$store.dispatch("alert/push", "<message here>")
-->
<template>
  <v-snackbar
    v-model="show"
    :timeout="1500"
    :multi-line="multiLine"
    top
    right
    @click="show = false"
    :color="isAlert ? 'error' : 'success'"
    location="top right"
  >
    {{ message }}
  </v-snackbar>
</template>
<script>
export default {
  data: () => {
    return {
      show: false,
    };
  },
  computed: {
    message() {
      return this.$store.state.alert.message;
    },
    timestamp() {
      return this.$store.state.alert.timestamp;
    },
    isAlert() {
      return this.$store.state.alert.isAlert;
    },
  },
  watch: {
    timestamp() {
      this.show = true;
    },
  },
};
</script>
