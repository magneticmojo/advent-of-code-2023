import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GearRatiosTest {

    private char[][] schematicSmall = {
            {'4', '6', '7', '.', '.', '1', '1', '4', '.', '.'},
            {'.', '.', '.', '*', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '3', '5', '.', '.', '6', '3', '3', '.'},
            {'.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
            {'6', '1', '7', '*', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '+', '.', '5', '8', '.'},
            {'.', '.', '5', '9', '2', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '7', '5', '5', '.'},
            {'.', '.', '.', '$', '.', '*', '.', '.', '.', '.'},
            {'.', '6', '6', '4', '.', '5', '9', '8', '.', '.'}
    };

    private char[][] schematic;

    @BeforeEach
    void setUP() throws URISyntaxException, IOException {
        String fileName = "gear-ratios.txt";

        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));

        // Assuming all lines have the same length and there's at least one line
        schematic = new char[lines.size()][lines.get(0).length()];

        // Fill the char array with characters from the file
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                schematic[i][j] = lines.get(i).charAt(j);
            }
        }

        for (char[] row : schematic) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    @Test
    void testCalculatesLarge() {
        GearRatios gR = new GearRatios(schematic);
        assertEquals(539590, gR.sumPartNumbers());
    }

    @Test
    void testA() {

        String data =
                """
                        ..224.....487...................718.....................378............................................284........310......313..........311.
                        """;

        String[] lines = data.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        GearRatios gr = new GearRatios(grid);
        assertEquals(0, gr.sumPartNumbers());
    }

    @Test
    void testB() {

        String data =
                """
                        ..224.....487...................718.....................378............................................284........310......313..........311.
                        ....*..............................*744....486*485......*......741......@...359.#666...439................*925....*......$..+........@......
                        .235................758..440...........................251....*......262.....*..........*......................752......774.......515.......
                        """;

        String[] lines = data.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        GearRatios gr = new GearRatios(grid);
        assertEquals(9861, gr.sumPartNumbers());
    }

    @Test
    void testC() {

        String data =
                """
                        ..224.....487...................718.....................378............................................284........310......313..........311.
                        ....*..............................*744....486*485......*......741......@...359.#666...439................*925....*......$..+........@......
                        .235................758..440...........................251....*......262.....*..........*......................752......774.......515.......
                        .........705%..@746........+..942*591.347.470...#..257.........637...........793.......299..../.....813....509......464......&.........688..
                        .....82................................*.../..901.....*..................836.....&............814...*........*..............80...17*....*...
                        .../...*...679.661.....299...........222.............875.....213...161............964...894.........998.....310....258.85...........735.586.
                        .650..23..#......*.......................................760*........@./........................202...................*.....339.............
                        ..............598......#.....536....702*.........705..........793......957............./........*...935...........965.......................
                        ..................*.357...+.....@.......242.......*......283..*...=956.......118.......959...184...*..............*....401*527.....348...161
                        .63=...955.523..77......978.846....849..........699..........790........=911.*.....87...............486.........914..............=..........
                        .........*.........842......*.......*..................*127....................844...*....946.......................235...209@...710.735....
                        ...........@.........*.....144....226....298........560.....253.........431......*...586.......723..../...482.......&...................*...
                        ...........871../918........................+....................329*......%.....906.............*...350.....@...............365.......361..
                        ......................536............................+..793..........664.....739................507......289.....256%...73..*...............
                        .356..834..............*.........96.939..............28.+....=..............*...........................*................$.553..........526.
                        ...*...*.............501..746....*..-.......=.................890.....+...701..............807........447......97$..%...........58......$...
                        .891....346...699...........*.421......+....666......198*791......340.433............#...%...*.......................316........*.....%.....
                        ..............%........81.984...........462....................../..........116...711....963...$................380.......745...85....224...
                        .........370......923..@.....................563...........188+.........326*...................67.815..............*582.....................
                        .........*........-........#530.........489........59...................................................#.....57.........100.*.......441*161
                        .637*....961...........118...........21*...........@..216+.........................+....432*.*309..64....992.......81......=.781.644........
                        .....324.........126......*166...............&...................385*..............233............*................*..436...........*517....
                        .88*.........390*................355..335...372....+254..............294...$589........&..803/.....38..-...........56..$....................
                        ....235.-.........+196...948....*........-................359.........................398.............608..../.................145..346.....
                        ........915.................-.485.945.................936*................114.............118...............490.536..............*.%........
                        .663........61.......856..........+....343....859...............*345.......*...=862............711.............................313.....606..
                        ........23...$.....@..................*......................316.....529.362.............49......................307*791....................
                        225...............281...#..222.....@..283..............675............*.........911*90..-.....914..%39....509...............................
                        .......797*....$........23..../...895.............67.......164.896.....554.....................*............=..............460........181...
                        ...........211.474...............................@...........*....*201..........911.717.#....192.75..679...........$.........*.........=....
                        ....355.........................&.....................678.650......................*.....128.....*.....*.........925..230...105.............
                        ....*.......409#.......*897.....698....427.........+....*.......*...............................313...670..521.......*....&.............*308
                        ...838..335.................367........-..........325....233...224...950......688.....199.774.........................793.108........501....
                        ...........*..102..924.349...+..595....................................*................&..............395+.....................753.........
                        .........318...*......*........*....676...759.154...................524...........908.........694.211................173...........*214..424
                        ....458........403...........958.98....*........*..............688......957...488..+...........*.../....................................*...
                        .......*.............591...............218.....203.....196$.......+....*........*......................765......971......./....927*741..227.
                        ......592..%60.$.....*.....885/....287...............................474.........409......226.174.......@........*.....230..................
                        ...............162.169.................510..962..............17......................32@..*...*.................225....................-....
                        .....880...................201.....823*........*...914.724.........89...&.......267=.....717.54.436..........60...............&.........69..
                        710........601....306.287.....+.........65...308....&...=.../......*.....926..................................*........278.415......-.......
                        ..............*..*......&..........241.%...................827../.265.............662.678......@...#......@.....$......-............734.144.
                        .............424..434.....701.....*........380*731.............24.................*...&........973..741....728..357.........................
                        ........304.................*...881................238.....740.........947........587...22...........................271*........603....@...
                        ...................266.....918....................*..........+.519.617..*...826..........*............./268..974.........486.376....*.295...
                        ...939.........363.*...............378..........302....586........*....740...=..........380........931...........................12.........
                        ...*..........-.....387...............*..588..............*..../...............315...................-...712..491.........956.....#.........
                        .180.454...................104..661...91...*.............811....370......471...-....419....804..........*...................*...............
                        .....*.......@../464........%..*............243.....578...................&..........*...........604..250.....784....750....998.............
                        .....245....165.................771...................&.......#.691...768....654....261.........................*.......*............566....
                        441..................91.....194.......643...932*............379.#....$.......*.................86.............940..263.638...........*......
                        ...+.............%.../.......#.........*........344...795..................991..........544....*..................*..........767...266......
                        .......-..708..727.....191$......*.....698....=...................990................+.....*....308.......@......325.........*.........44...
                        ...18.397....=.....93.........649.900.........62....-........322.....*.......@......339....619..........404..........540........./....*.....
                        ..../...............%............................742............*...........196................874.205........96#..............203.224......
                        .........704...727.................496.......*........135....769........./.................%....&....*.../..=.........757...................
                        ........*........*..424..........&........714.5........*...........@....884...............114.......674.144..83..........*302...........%...
                        ........29....179..*............104..................531....629.455..................473......300....................254..............640...
                        ..................296.......................................................1........*.......%..............548........*.....103............
                        ....381...........................365#..713.282.......%744....................../....709...#...=...431...$.*......*.....837.....*9.....*....
                        ......*.90.....347.179#...................%.......359..........92............304...*.....923.446.......385.699.....749..............193.206.
                        ....922.+.......*.......-197.....................*.......128........273....@.....758..............439...................650*54.736#.........
                        ................817..-..........152..594..949..807.......$...224...*.....124............889.......=...................................625...
                        ....139....667......239........*...........*.......387........%..806........../...........*..788....632...........=8......584-......*.%.....
                        ...........*.................280...........11.........*726............181......761..-....66.....%....-................64...........14.......
                        ............742.736.725..........708.667..........332.......828*750......-.........425.......................944.....*...................926
                        .719...110...........*.....348...=..........121......*........................................*.......961.....*.......971...........+.......
                        ..../.....*629........830..*............91....&..241..491...930............561................889....*....710.333..............163...311....
                        ......%...................252.../..................*...........*270.......................165.....208.......*..................../.......694
                        ...914...126...267.183........952..$645......414.822................................69.......-..........=..112.464..267....359..........*...
                        .............7*..........................806.../.....931.....*.....355..............*....928...........854.........-..............*993...101
                        439.897...........%...........8......381...*........*....748..987....*............437.......*....954.......................*................
                        .......*595....783........49.....110.$...675...8...60.....=........981........&..........730....*.............336.......120.790..........386
                        ............90..............*212...*..........*.......985.....153........*..12...912*383......665.....754.....-.....................950.....
                        ......790#.*........896@.........773....777..581..514*....910.....5.....579.............................=.......#.....................*.....
                        ...&.......496...........347..............*...............*...................../............................790..............334......912..
                        692............609....&.......694..........693...213....620......695......871..385.......82..........785.......................*..163.......
                        .........#....*......984.........*.................=............*.......................................*.545...-............978.....*......
                        ......971...477.............465......196*313..........447.......766......49.......126*480.............798......792..359*193...........837...
                        ................157...........+..................773.=..............752$....................447*565..........................*53............
                        .384%..........@.........449............470..749...@.........@792..............138..527.......................339.....299.505...........287.
                        ......691........700........@......96..........*........721*.....................@....*...942......845........*......-.........713..........
                        .....*......260/...*...........................491..452.....336.......................73....+..960..........238...........163$..&...=.@.....
                        ....689.............831...282....709.960.............=.............204............................#..............59...97..........199..111..
                        ........................$........../........-....498......................557....&...........972............450.............................
                        .............653........476...398.........619...*.....*280..923..............*31..691.........*........=...*.....67......782................
                        ..............*....%..........*.........+......268...........&........428....................408....740....576....*.......*.........$.......
                        ....-../...734......317.114.332...795...743.........................-..=..908...........381.......................914...420.*647....528.....
                        ..284.825...............*......................721/.=929.........324.....*....167.561...*.....815*466.......................................
                        ..............389..304.480.......851..@....585..............942%......#.496....*....-.53................170....938*..........518............
                        ........368..*.....@..............*....941..+.......*...............930.........364.......522......................59.........+....$....652.
                        ...........+.963...................949...........828.784......860............*......420.....*..................*......548...........310.*...
                        .....476...............*...............=...94.+..................&........589.833....*..426.940.....&.....959.993.&88....................398
                        .......*................624....917...838..*...664.......721........................833...$.......672......$............19...................
                        ....350.......................&....=..............333.................707....................................240..336....=...746.263..$463..
                        .........................-.........220.....663...../.............*849..#..631.....712....=.../...308.580.............*..........*...........
                        .78@......../..........422....444.............*...............281............*...*....447..37.......*................814....................
                        ........./...788...810.........&..............369..985....-................92................................684...............74...........
                        ......279.........*......128/....464........#...../......944........978...............163..984*5.@............+.......165......*....285.....
                        ..................889.............*.......969........................%.......5......&....$........272....................*......374.........
                        .........&..............@948..*..13.358........796.............632............*....789........91*..............*66........152...............
                        ........508...+..............3...........544.....$.517.....703....*774.......434............/....794........932.............................
                        ...$...........241.....74...........974..*...954............*.......................375..772...........218.........414...........*366....252
                        .501..566...............*..........*....599.....#........256.....468@..942.101.........*..........156...*.............*.......998...........
                        ...../.............263.312....+..767..............#.........................*...&95..838.162=.....+....130.........583..544.......176.......
                        ............................936......826.......421.......-139.......982...893.......................*..................*........./..........
                        .284.....=724...................661.$....694........85..........438.............51...............616.698.*36.....262......357+........224...
                        ....@..........491........+........................@......193......*596...........%../844...387.................$.....132.........%.........
                        .......84..............362.................................*..98..........&................*....14....394..........$..............56........
                        .........*432.....512................704......424.............*............74........196%..875...*.....*............132.......229...........
                        ..............723*.........568........*..108........672......55.172...217.........................289.427........................*..........
                        .........#795.........214....*.336...94....*....988................%..&.............*86......*...............838&.......540.......902.......
                        .922...........288........719.....*........845...&..558......#...................524......195.704.....................................77....
                        ...*....842....../...166........883...-................*503..451..305../....600............................900..280.....*169...586..........
                        751....*.........../.*......944......116....416.........................877....*....418...194........644..*....=.....244..........#.....354.
                        .......118......208...................................788...................826........$....$...207.....@..152...........893*608.......&....
                        ..948.......................203../....206.............*....341./977.916.........49...#........@....-............+385........................
                        ...*......266.................*...607...@.990......964..............-..........*.....340...697........62..............526.....-.............
                        ..675.....*..........612....604.............*..............492.884...........212...............*157....*...679.........*....706...716%.518..
                        ......103.749..989...*...................739..............$......=..=..................212..684.....35.......*.........785..............+...
                        ..343*......../.......680.....................*423.................476....&....420..............241..*......532.....+.......864$.....&......
                        ..................826........403..311...60.451.............+...........562....../.......306.......*..............420..................321...
                        ...241..............*...........+..=....@......*240.......845....@.........740.................968..389.177*...................640..........
                        ......*82...........963...211%...............38...............901.....404.........&..253..............-.....37.........218.501...*....320...
                        ....*...........................#215...264......298........................*970..801....*....569...............3.......*......%..859.*......
                        .136.818...212...........91*72........*.........-...750.....775*728.29.270...............984.&...371..........*.........639..........670....
                        ...........*.../959................438.....524......*...943.........*................755...................162.................446#.........
                        ..........290.............751................*.....557..*...483.............421.........*............189.%................/.................
                        ....................9.786..-......835.......978........445.%..........350........860..662.......#...*....354........*.....682...............
                        .117..57+....441...*....+............................................../............*.........88....142..........%.927......................
                        ...*...........*..402....................874.....734.926$../...............*633......219.84/............662...205...................334.....
                        ...981........367........338...332/.........=......*......119....375#.334.......146..............%.....$................384.............-511
                        ........165................=....................666.....................#..614.....-..%744..617.887.......848*..............................
                        ...........*.203.593...158....*..........152..............+817.....866.......&...............*......../.......904........701.165.80.........
                        404..977.93..*....*....*...396.281......*....953....*................*.........116..97.70=..179....107..338................$...*....622.....
                        ......=.....445........................382.....*...672................859......*...%....................&..........-.571......717....*......
                        ..................240............175..........808..............225..........988............/604..............232.448..*..651......769.......
                        .........569*....*........975*.....*....968..............585.....*................26.................................394....@.142...........
                        ......*......498..969.........360.666...%.........................919.......360........-.*.........%...................................484..
                        ...407................886...................................84......................933...101....58........839..425.........................
                        """;

        String[] lines = data.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        GearRatios gr = new GearRatios(grid);
        assertEquals(539590, gr.sumPartNumbers());
    }


}