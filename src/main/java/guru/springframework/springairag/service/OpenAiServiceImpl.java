package guru.springframework.springairag.service;

import guru.springframework.springairag.model.Answer;
import guru.springframework.springairag.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImpl implements OpenAiService {
    private final ChatModel chatModel;
    private final SimpleVectorStore simpleVectorStore;

    public OpenAiServiceImpl(ChatModel chatModel, SimpleVectorStore simpleVectorStore) {
        this.chatModel = chatModel;
        this.simpleVectorStore = simpleVectorStore;
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());

    }
}
