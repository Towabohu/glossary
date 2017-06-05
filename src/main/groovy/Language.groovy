/************************************************************************
 * This is free software - without ANY guarantee!
 *
 *
 * Copyright Dr. Gernot Starke, arc42.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *********************************************************************** */

enum Language {

    EN('English', 'EN'),
    DE("German (Deutsch)", "DE"),
    FR("French (Français)", "FR"),
    ES("Spanish (Espaniol)", "ES")

    final String name
    final String shortName

    Language(name, shortName) {
        this.name = name
        this.shortName = shortName
    }

    String toString() {
        return name
    }


    static Language getLanguageByShortName(String shortName) {
        return shortName as Language

    }

    static Language valueOfName(String name) {
        values().find { it.name == name }
    }

    static public List<LanguagePair> languagePermutations(List<Language> languages) {

        // pairs of zero or one language don't make any sense - abort
        assert languages.size() >= 2

        // at lease we now have TWO languages
        return createPairs(languages.head(), languages.tail())

    }


    private static createPairs(Language sourceLang, List<Language> otherLanguages) {
        if (otherLanguages.size() == 1) {
            // no more pairs to be found
            return [
                    new LanguagePair(sourceLang, otherLanguages.head() as Language),
                    new LanguagePair(otherLanguages.head() as Language, sourceLang)
            ]
        } else {
            List<LanguagePair> pairs = new ArrayList<LanguagePair>()
            otherLanguages.each { otherLanguage ->
                pairs.add(new LanguagePair(sourceLang, otherLanguage))
                pairs.add(new LanguagePair(otherLanguage, sourceLang))
            }
            return createPairs(otherLanguages.head(), otherLanguages.tail()) + pairs
        }
    }
}
