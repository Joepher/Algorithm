package leetcode;

public class Solution1163 {
    public static String lastSubstring(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int from = chars.length - 1;
        for (int i = from - 1; i >= 0; --i) {
            if (chars[i] > chars[from]) {
                from = i;
                continue;
            }
            if (chars[i] == chars[from]) {
                boolean needChange = true;
                for (int j = 1; j < chars.length - from; ++j) {
                    if (chars[i + j] > chars[from + j]) {
                        break;
                    }
                    if (chars[i + j] < chars[from + j]) {
                        needChange = false;
                        break;
                    }
                    if (i + 1 == from) {
                        break;
                    }
                }
                if (needChange) {
                    from = i;
                }
            }
        }
        return s.substring(from);
    }

    public static void main(String[] args) {
        System.out.println(lastSubstring("abab"));
        System.out.println(lastSubstring("leetcode"));
        System.out.println(lastSubstring("cacacb"));
        System.out.println(lastSubstring("cacaca"));
        System.out.println(lastSubstring("abab"));
        System.out.println(lastSubstring("xbylisvborylklftlkcioajuxwdhahdgezvyjbgaznzayfwsaumeccpfwamfzmkinezzwobllyxktqeibfoupcpptncggrdqbkji"));
        System.out.println(lastSubstring("pgtrdjdpscrwjifnrcttyruighgygsuvlhxpckkeahrupvnhlnpulyogsbktcuxnmnbmgadksxdjunqvmzyujynwzevtstjvzkddxjjmbgxfueteeuktvcbvypbdnzostbwxmxdwomguuymexfrrwuvuglgwmmwpkrqrpuzvjujksdwopsqlsrfgyzhymfgejuwhyvoqoxluvsdnmkglypoozrcgnzchpurezauixujddjjawqiaasvhrhfbhsqutgskudpcbqkkrcagrtalnsecxmlbiysgabvjbwpfufiwqdsnwbutashtejpmcypfztbgzuxwfcpkwdzhvfxbtvdvdaufjpqgfgxufhsopvwmgekcjejdlgxtdghmyzvopqkdzpuudyunnafvaeyshluuujkqncyigweelxmvgiaegonqmouaxwaqxhnnvaeuppsritdsymdwonbooswiolhbacpnehyvbhekxqxuvpnaxrfgeyixmurlgszqrotqynvtlbpjhhwdkbneiutmwutiyqegkxsrgldqvziysvihgbplhiumonlzlfzbuavbygmltdnfwjbspfpmzkfryjjpswihwixmbeodfpnmytccdvjnctzkxuqrvgjehodfdhpconhfpnatzzxyqkzjttgnwvxcfwyhlajvuhjonbpkvbbahlbybvvnhrwnnpnagtnnqcewzdzsxjgfoqbipohzsgpmyhqgvvnffjumirbhpbfletvcglthgwdvkgczucreovnfpbugyzuugqodpgsylfwyjucfnxjbghurnrkkuwsjfxeoxyzvltwhzyuechbfwstfovqgxngcwsmqsbzasrfrdvjcgedacvviihnzlijbaotpkvzmfejtfsnljthmzfhsrtieqlfhuatdwhcvsoxyphqsoxaqiqjcbuldswtjsukvcoowyfgmixswlyvkllvdtnerfisymrwgtfleupfxxswdlhvioei"));
    }
}
