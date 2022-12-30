function solution(id_pw, db) {
    let ans = "";
    db.forEach(el => {
        if (el[0] === id_pw[0]) {
            if (el[1] === id_pw[1]) {
                return ans = "login";
            } else return ans = "wrong pw";
        }
    });
    return ans ? ans : "fail";
}

console.log(solution(["meosseugi", "1234"], [["rardss", "123"], ["yyoom", "1234"], ["meosseugi", "1234"]]))