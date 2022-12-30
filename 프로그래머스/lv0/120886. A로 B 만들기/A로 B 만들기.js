function solution(before, after) {
    if (before.split("").sort().join("") === after.split("").sort().join("")) {
        return 1
    } else {
        return 0;
    }
}