import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 위, 아래, 왼쪽, 오른쪽
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	// 출력을 위한 화면 map
	static char[][] map;
	// 해당 위치의 실제 정보를 담고 있는 map
	static Object_info[][] info_map;
	// 플레이어 class
	static Player player;
	static int[] start;
	// 각각의 장신구를 가지고 있는가?
	static boolean O_HR = false;
	static boolean O_RE = false;
	static boolean O_CO = false;
	static boolean O_EX = false;
	static boolean O_DX = false;
	static boolean O_HU = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int number_item = 0;
		int number_monster = 0;
		map = new char[H][W];
		info_map = new Object_info[H][W];
		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 'B')
					number_item++;
				if (map[i][j] == '&' || map[i][j] == 'M')
					number_monster++;
				if (map[i][j] == '@') {
					map[i][j] = '.';
					player = new Player(i, j, 1, 20, 2, 2, 0, 20, 5);
					start = new int[] { i, j };
				}
			}
		} // 맵 정보 입력 + 플레이어 확인.

		// 행동 커맨드.
		String command = br.readLine();

		for (int i = 0; i < number_monster; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			String name = st.nextToken();
			int ATT = Integer.parseInt(st.nextToken());
			int DEF = Integer.parseInt(st.nextToken());
			int HP = Integer.parseInt(st.nextToken());
			int get_EXP = Integer.parseInt(st.nextToken());
			info_map[r][c] = new Object_info("monster", name, ATT, DEF, HP, get_EXP);
		} // 몬스터 정보 입력

		for (int i = 0; i < number_item; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			String name = st.nextToken();
			if (name.equals("O")) {
				String item_type = st.nextToken();
				info_map[r][c] = new Object_info("item", name + "_" + item_type, 0, 0);
			} else if (name.equals("W")) {
				int ATT = Integer.parseInt(st.nextToken());
				info_map[r][c] = new Object_info("item", name, ATT, 0);
			} else {
				int DEF = Integer.parseInt(st.nextToken());
				info_map[r][c] = new Object_info("item", name, 0, DEF);
			}
		} // 아이탬 정보 입력

		int now_turn = 1;
		while (true) {
			// 커멘드가 다 돌았지만 게임이 끝나지 않았을 때.
			if (command.length() < now_turn) {
				map[player.r][player.c] = '@';
				print(now_turn - 1);
				System.out.println("Press any key to continue.");
				return;
			}

			// 커맨드에 맞게 케릭터를 움직여 준다.
			int R, C;
			if (command.charAt(now_turn - 1) == 'U') {
				R = player.r + dr[0];
				C = player.c + dc[0];
			} else if (command.charAt(now_turn - 1) == 'D') {
				R = player.r + dr[1];
				C = player.c + dc[1];
			} else if ((command.charAt(now_turn - 1) == 'L')) {
				R = player.r + dr[2];
				C = player.c + dc[2];
			} else {
				R = player.r + dr[3];
				C = player.c + dc[3];
			}
			if (0 <= R && R < H && 0 <= C && C < W && map[R][C] != '#') {
				player.r = R;
				player.c = C;
			}

			R = player.r;
			C = player.c;
			// 스파이크 함정에 걸렸다!
			if (map[R][C] == '^') {
				// 주인공이 O_DX 아이탬을 가지고 있는가?
				if (!O_DX) {
					player.HP = Math.max(0, player.HP - 5);
				} else {
					player.HP = Math.max(0, player.HP - 1);
				}

				if (player.HP == 0) {
					if (O_RE) {
						// O_RE장신구를 지우고 시작위치로 돌려보낸다.
						O_RE = false;
						for (int i = 0; i < player.having_O.size(); i++) {
							if (player.having_O.get(i).equals("O_RE")) {
								player.having_O.remove(i);
								break;
							}
						}
						player.HP = player.MAX_HP;
						player.r = start[0];
						player.c = start[1];
					} else {
						// 끄앙 사망
						print(now_turn);
						System.out.println("YOU HAVE BEEN KILLED BY SPIKE TRAP..");
						return;
					}
				}
			} // 스파이크 함정

			// 아이탬을 주웠다!
			if (map[R][C] == 'B') {
				// 무기
				if (info_map[R][C].item_type.equals("W")) {
					// 무기 교체
					player.W = info_map[R][C].ATT;
				}
				// 방어구
				else if (info_map[R][C].item_type.equals("A")) {
					// 방어구 교체
					player.A = info_map[R][C].DEF;
				}
				// 무기와 방어구가 아니면 싹다 장신구 겠죵?
				else {
					// 보유 장신구가 4개 미만 && 해당 장신구를 가지고 있지 않다!
					if (player.having_O.size() < 4 && !check_O(info_map[R][C].item_type)) {
						player.having_O.add(info_map[R][C].item_type);
						update_O(info_map[R][C].item_type);
					}
				}
				info_map[R][C] = null;
				map[R][C] = '.';
			} // 아이탬 획득

			// 몬스터와 조우했다!
			if (map[R][C] == '&') {
				in_Battle(R, C, info_map[R][C]);
				// 혹시 죽었니?
				if (player.HP == 0) {
					// 끄앙 사망
					print(now_turn);
					System.out.println("YOU HAVE BEEN KILLED BY " + info_map[R][C].monster_name + "..");
					return;
				}
				info_map[R][C] = null;
			}

			// 보스 몬스터와 조우했다!
			if (map[R][C] == 'M') {
				in_Battle(R, C, info_map[R][C]);
				// 혹시 죽었니?
				if (player.HP == 0) {
					// 끄앙 사망
					print(now_turn);
					System.out.println("YOU HAVE BEEN KILLED BY " + info_map[R][C].monster_name + "..");
					return;
				}
				// 게임을 클리어 했다! 보스를 주겼...다?
				else {
					// 혹시 부활아이탬 쓴 거 아니지?
					if (map[R][C] == '.') {
						map[R][C] = '@';
						print(now_turn);
						System.out.println("YOU WIN!");
						return;
					}
				}
			}
			now_turn++;
		} // RPG 시뮬레이터

	}// main

	static boolean check_O(String s) {
		for (int i = 0; i < player.having_O.size(); i++) {
			if (player.having_O.get(i).equals(s))
				return true;
		}
		return false;
	}// check_O

	static void update_O(String s) {
		if (s.equals("O_HR"))
			O_HR = true;
		if (s.equals("O_RE"))
			O_RE = true;
		if (s.equals("O_CO"))
			O_CO = true;
		if (s.equals("O_EX"))
			O_EX = true;
		if (s.equals("O_DX"))
			O_DX = true;
		if (s.equals("O_HU"))
			O_HU = true;
	}// 장신구 상태 업데이트

	static void in_Battle(int R, int C, Object_info monster) {
		int turn = 1;
		int monster_HP = monster.HP;
		// 보스 장신구 && 보스전 시작임 일단 에너지 풀로 채우고 시작
		if (map[R][C] == 'M' && O_HU)
			player.HP = player.MAX_HP;

		while (true) {
			// 주인공은 언제나 선빵이다!
			if (turn == 1 && O_CO && O_DX) {
				monster.HP = Math.max(0, monster.HP - (Math.max(1, (player.ATT + player.W) * 3 - monster.DEF)));
			} else if (turn == 1 && O_CO && !O_DX) {
				monster.HP = Math.max(0, monster.HP - (Math.max(1, (player.ATT + player.W) * 2 - monster.DEF)));
			} else {
				monster.HP = Math.max(0, monster.HP - (Math.max(1, player.ATT + player.W - monster.DEF)));
			}
			// 혹시 몬스터가 뒤졌니?
			if (monster.HP == 0) {
				if (O_EX) {
					player.EXP += (int) monster.get_EXP * 1.2;
				} else {
					player.EXP += monster.get_EXP;
				}
				if (O_HR)
					player.HP = Math.min(player.MAX_HP, player.HP + 3);
				update_player();
				map[R][C] = '.';
				return;
			}
			// 몬스터가 안죽었다! 공격들어온다!
			if (turn == 1 && map[R][C] == 'M' && O_HU) {
				// 보스전이면서 턴1이다? HU아이탬 있으면 이번턴 적은 자동 턴넘김.
				turn++;
				continue;
			} else {
				player.HP = Math.max(0, player.HP - (Math.max(1, monster.ATT - (player.DEF + player.A))));
			}
			// 혹시 플레이어가 뒤졌니?
			if (player.HP == 0) {
				if (O_RE) {
					// O_RE장신구를 지우고 시작위치로 돌려보낸다.
					O_RE = false;
					for (int i = 0; i < player.having_O.size(); i++) {
						if (player.having_O.get(i).equals("O_RE")) {
							player.having_O.remove(i);
							break;
						}
					}
					monster.HP = monster_HP;
					player.HP = player.MAX_HP;
					player.r = start[0];
					player.c = start[1];
					return;
				} else {
					return;
				}
			}
			turn++;
		} // 전투중
	}// 전투 시뮬레이션

	static void update_player() {
		if (player.EXP >= player.MAX_EXP) {
			player.LV += 1;
			player.MAX_EXP = player.LV * 5;
			player.MAX_HP += 5;
			player.ATT += 2;
			player.DEF += 2;
			player.HP = player.MAX_HP;
			player.EXP = 0;
		}
	}// 레벨업!

	static void print(int now_turn) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		sb.append("Passed Turns : " + now_turn + "\n");
		sb.append("LV : " + player.LV + "\n");
		sb.append("HP : " + player.HP + "/" + player.MAX_HP + "\n");
		sb.append("ATT : " + player.ATT + "+" + player.W + "\n");
		sb.append("DEF : " + player.DEF + "+" + player.A + "\n");
		sb.append("EXP : " + player.EXP + "/" + player.MAX_EXP);
		System.out.println(sb.toString());
	}

	static class Object_info {
		// item, monster
		String type;
		String item_type, monster_name;
		int ATT, DEF, HP, get_EXP;

		// monster constructor
		public Object_info(String type, String monster_name, int aTT, int dEF, int hP, int get_EXP) {
			this.type = type;
			this.monster_name = monster_name;
			ATT = aTT;
			DEF = dEF;
			HP = hP;
			this.get_EXP = get_EXP;
		}

		// item constructor
		public Object_info(String type, String item_type, int aTT, int dEF) {
			this.type = type;
			this.item_type = item_type;
			ATT = aTT;
			DEF = dEF;
		}
	}// Object_info

	static class Player {
		int r, c;
		int LV, HP, ATT, DEF, EXP;
		int MAX_HP, W, A, MAX_EXP;
		ArrayList<String> having_O = new ArrayList<>();

		public Player(int r, int c, int lV, int hP, int aTT, int dEF, int eXP, int mAX_HP, int mAX_EXP) {
			this.r = r;
			this.c = c;
			LV = lV;
			HP = hP;
			ATT = aTT;
			DEF = dEF;
			EXP = eXP;
			MAX_HP = mAX_HP;
			MAX_EXP = mAX_EXP;
		}
	}// Player
}