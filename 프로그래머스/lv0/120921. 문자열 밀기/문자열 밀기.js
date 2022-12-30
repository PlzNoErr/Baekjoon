function solution(A, B) {
    let push = function (a) {
        return a.charAt(a.length - 1) + a.slice(0, a.length - 1);
    }

    if (A === B) return 0;

    let time = 0;
    while (time <= A.length) {
        A = push(A);
        if (A === B) return ++time;
        else time++;
    }

    return -1;
}

console.log(solution("hello", "ohell"));