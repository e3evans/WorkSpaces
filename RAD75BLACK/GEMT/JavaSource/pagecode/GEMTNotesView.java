/**
 * 
 */
package pagecode;

import javax.faces.component.UIParameter;

/**
 * @author Eric Evans
 *
 */
public class GEMTNotesView extends PageCodeBase {

	protected UIParameter param1;

	protected UIParameter getParam1() {
		if (param1 == null) {
			param1 = (UIParameter) findComponentInRoot("param1");
		}
		return param1;
	}

}