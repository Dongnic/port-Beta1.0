import { createStore } from 'vuex'

import { module1 } from '@/store/modules/module1'
import { module2 } from '@/store/modules/module2'

export default createStore({
  modules: { module1, module2 }
})
