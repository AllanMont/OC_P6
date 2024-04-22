/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {},
    screens: {
      'max-2xl': {'max': '1535px'},
      // => @media (max-width: 1535px) { ... }
      'min-2xl': {'min': '1535px'},

      'max-xl': {'max': '1279px'},
      // => @media (max-width: 1279px) { ... }
      'min-xl': {'min': '1279px'},

      'max-lg': {'max': '1023px'},
      // => @media (max-width: 1023px) { ... }
      'min-lg': {'min': '1023px'},

      'max-md': {'max': '767px'},
      // => @media (max-width: 767px) { ... }
      'min-md': {'min': '767px'},

      'max-sm': {'max': '639px'},
      // => @media (max-width: 639px) { ... }
      'min-sm': {'min': '640px'},
    }
  },
  plugins: [],
}

