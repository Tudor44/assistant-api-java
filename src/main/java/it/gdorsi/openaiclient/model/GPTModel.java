package it.gdorsi.openaiclient.model;

public enum GPTModel {
    @SuppressWarnings("unused") GPT3_5_TURBO("gpt-3.5-turbo"), //balance between cost and efficiency
    @SuppressWarnings("unused") GPT4_1106_PREVIEW("gpt-4-1106-preview"); //best capabilities to date, but almost 15x more expensive than 3.5-turbo

    private final String modelName;

    GPTModel(String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return modelName;
    }
}
