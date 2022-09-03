import { extendObservable } from "mobx";

class UserStore {
    constructor() {
        extendObservable(this, {
            loading: false,
            token: '',
            refresh: '',
            user_id: 0,
            role: ''
        })
    }
}
export default new UserStore();
