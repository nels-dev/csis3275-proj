<!--
    This is a wrapper for v-alert component to ensure consistent look-and-feel
    Usage: <FormAlert :message="error" />
-->
<template>
  <v-alert
    variant="outlined"
    type="warning"
    prominent
    border="top"
    class="ma-5"
    v-if="message"
  >
    {{ message }}
  </v-alert>
</template>

<script>
export default {
  props: ["message"],
};
</script>
