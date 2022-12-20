export default function authHeader() {
    if (localStorage.getItem("token")) {
        return { Authorization: 'Bearer' + localStorage.getItem("token") };
    } else {
        return {}
    }
}
