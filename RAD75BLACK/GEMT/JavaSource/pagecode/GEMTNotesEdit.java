/**
 * 
 */
package pagecode;

import javax.faces.component.html.HtmlOutputText;

/**
 * @author Eric Evans
 *
 */
public class GEMTNotesEdit extends PageCodeBase {

	protected HtmlOutputText gemt_notes_content;

	protected HtmlOutputText getGemt_notes_content() {
		if (gemt_notes_content == null) {
			gemt_notes_content = (HtmlOutputText) findComponentInRoot("gemt_notes_content");
		}
		return gemt_notes_content;
	}

}