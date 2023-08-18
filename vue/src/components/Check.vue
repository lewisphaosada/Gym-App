<template class="check-component">
  <div class="center-container">
    <button class="check-button" v-on:click="onClick">
      <img
        v-bind:src="
          checkedStatus
            ? 'https://media.istockphoto.com/id/1289427302/vector/check-out-button-rounded-sign-on-white-background.jpg?s=612x612&w=0&k=20&c=_8X-Ho3T2EJ3QPVs5Is7ahaU5hh2BRldKLbWxKPFvUo='
            : 'https://media.istockphoto.com/id/1289427422/vector/check-in-button-sticker-banner-rounded-glass-sign.jpg?s=612x612&w=0&k=20&c=5EhSNKqgoMeSrhbr5-n5jMVFiQS2m1NiavhF5wAOLsM='"
        v-bind:alt="checkedStatus ? 'Check Out' : 'Check In'"
        class="check-image"
      />
    </button>
    <div class="background-animation" ref="backgroundAnimation">
    </div>
  </div>
</template>

<script>
export default {
  name: "check",
  computed: {
      checkedStatus() {
        return this.$store.state.isCheckedIn;
      },
      sessionStartTime() {
        return this.$store.state.sessionTimerStart;
      },
      sessionEndTime() {
        return this.$store.state.sessionTimerEnd;
      },
      sessionElapsed() {
        return this.$store.state.sessionTimerEnd - this.$store.state.sessionTimerStart;
      }
  },
  methods: {
    toggleCheck() {
      this.$store.commit('TOGGLE_CHECK');
    },
    onClick() {
      if(this.checkedStatus) {
        if(!confirm("Are you sure you're done?")) {
          this.$store.state.isCheckedIn = true;
          return;
        }
        this.$store.commit('STOP_TIMER');
      } else {
        this.$store.commit('START_TIMER');
        this.$router.push({path: '/machines'});
      }
      this.toggleCheck();
    },
    startSession() {
      this.$store.commit('START_TIMER');
    },
    endSession() {
      if(confirm("Are you sure you're done?")) {
        this.$store.commit('STOP_TIMER');
      } else {
        this.$store.state.isCheckedIn = true;
      }
    },
    createFallingStrings() {
      const stringsArray = [
  "Sweat it out!",
  "Keep pushing!",
  "Stronger today.",
  "You've got this!",
  "Run the day.",
  "Train, don't strain.",
  "Stay active!",
  "Rise and grind.",
  "Feel the burn.",
  "Just keep moving.",
  "Stay committed.",
  "Never give up.",
  "One rep at a time.",
  "Push limits!",
  "Work hard, play hard.",
  "No pain, no gain.",
  "Make it count.",
  "Fuel for fitness.",
  "Own your workout.",
  "Be your best.",
  "Train like a champ.",
  "Crush your goals.",
  "Focus and train.",
  "Sweat, smile, repeat.",
  "Train for progress.",
  "Beast mode on!",
  "Break a sweat.",
  "Find your strength.",
  "Make sweat your best accessory.",
  "Strong mind, strong body.",
  "Never skip a beat.",
  "Chase your fitness.",
  "Workout warrior!",
  "Elevate your energy.",
  "Train with heart.",
  "Move with purpose.",
  "Keep the fire burning.",
  "Sore today, strong tomorrow.",
  "Transform through sweat.",
  "Healthy habits rock.",
  "Go beyond limits.",
  "Determination wins.",
  "Earn your results.",
  "Fit life, happy life.",
  "Embrace the challenge.",
  "Stay active, stay young.",
  "Fitness fuels confidence.",
  "Sweat your way up.",
  "Shine through sweat.",
  "You vs. you"
];

      stringsArray.forEach((string, index) => {
        const fallingText = document.createElement("div");
        fallingText.textContent = string;
        fallingText.classList.add("falling-text");
        fallingText.style.left = `${Math.random() * 100}%`;
        fallingText.style.animationDelay = `${index * 2}s`;
        this.$refs.backgroundAnimation.appendChild(fallingText);
      });
    }
  },
  mounted() {
    this.createFallingStrings(); // Call the method to add falling strings on component mount
  }
};
</script>

<style>
.center-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; /* Ensure container takes at least viewport height */
}


.check-button {
  border: none;
  background-color: transparent;
  outline: none;
  display: grid;
  z-index: 1;
  overflow: hidden;
  border-radius: 50%; /* Set border-radius to make it a circle */
  width: 500px; /* Adjust the width to control the oval shape */
  height: 600px; /* Adjust the height to control the oval shape */
  transform: scaleX(2); /* Scale horizontally to achieve an oval shape */
  margin-bottom: 250px;
}

.check-image {
  display: flex;
  flex-grow: 1;
  justify-content: center;
  align-content: center;
  width: 100%; /* Ensure image covers the button */
  height: 100%; /* Ensure image covers the button */

}

.background-animation {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  z-index: -1;
}

.falling-text {
  position: absolute;
  font-size: 16px;
  color: #000000;
  opacity: 1;
  animation: fall 10s linear infinite;
  transform: translateY(-100vh); /* Move elements above the viewport initially */
  z-index: 0;
}

@keyframes fall {
  0% {
    transform: translateY(-100%);
    opacity: 1;
  }
  100% {
    transform: translateY(100vh);
    opacity: 0;
  }
}

.falling-text:nth-child(odd) {
  left: 10%;
}

.falling-text:nth-child(even) {
  left: 80%;
}

</style>