export function getLocalRefreshToken() {
    const user = JSON.parse(localStorage.getItem("user"));

    return user ? user.refresh : null;
}

export function getLocalAccessToken() {
    const user = JSON.parse(localStorage.getItem("user"));
    return user?.access;
}

export function updateLocalAccessToken(token) {
    let user = JSON.parse(localStorage.getItem("user"));
    user.access = token;
    localStorage.setItem("user", JSON.stringify(user));
}

export function getUser() {
    return JSON.parse(localStorage.getItem("user"));
}

export function setUser(user) {
    localStorage.setItem("user", JSON.stringify(user));
}

export function removeUser() {
    localStorage.removeItem("user");
}
