package com.manpower.translations.actions;

import org.apache.commons.lang.StringUtils;

/**
 * Declared tasks actions to be used step2.
 */
public enum ActionTask {

	ADD_LANGUAGE("add_language"),

	REMOVE_LANGUAGE("remove_language"),

	VERIFY_DEFAULT("verify_default"),

	DEFAULT("default");

	private String value;


	private ActionTask(final String value){
		this.value = value;
	}

	/**
     * @param task task name
     * @return ActionTask related to the String task parameter,
     * null if not task found.
     */
    public static ActionTask getActionTask(final String task) {
    	ActionTask result = DEFAULT;

    	if(!StringUtils.isEmpty(task)){
    		final ActionTask[] listOfTasks = ActionTask.values();

            for (final ActionTask taskEnum : listOfTasks) {

                if (taskEnum.getValue().equalsIgnoreCase(task)) {
                    return taskEnum;
                }
            }
    	}

        return result;
    }

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
