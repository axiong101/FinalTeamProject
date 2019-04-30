package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class QuizGraph {
  private TreeMap<String, ArrayList<QuestionNode>> graph; 
  // Arraylist that contains an arraylist of strings
  // index 0 of the second arraylist holds the vertexes in
  // the graph.
  // the index after 0 hold the other vertexes that it points to
  private int questionNum;
  private int topicNum;
  private Set<String> topicSet;
  private List<String> questionList;
  

  /*
   * Default no-argument constructor
   */
  public QuizGraph() {
    graph = new TreeMap<String, ArrayList<QuestionNode>>();
    questionNum = 0;
    topicNum = 0;
    topicSet = new TreeSet<String>();
    questionList = null;
  }

  /**
   * Add new topic to the graph.
   *
   * If topic is null or already exists, method ends without adding a topic or throwing an
   * exception.
   * 
   * @param vertex
   * Valid argument conditions: 1. topic is non-null 2. topic is not already in the graph
   */
  public void addTopic(String vertex) { 
    //adds a topic to the string
    if (vertex != null) {
      graph.put(vertex, new ArrayList<QuestionNode>());
      topicNum++;
    }
  }

  /**
   * Remove a topic and all associated questions from the graph.
   * 
   * If topic is null or does not exist, method ends without removing a topic, question, or throwing
   * an exception.
   * 
   * @param vertex
   * Valid argument conditions: 1. topics is non-null 2. topic is not already in the graph
   */
  public void removeTopic(String vertex) { 
    // remove a topic from list
    if (vertex != null) {
      if (graph.containsKey(vertex)) {
        graph.remove(vertex);
        topicNum--;
      }
    }
  }

  /**
   * Add the question with topic and question. (edge is directed and unweighted) If either
   * topic or question is not present then no questions will be added and no exceptions will be thrown. 
   * If the question exists in the
   * graph, no question is added and no exception is thrown.
   * 
   * @param String topic
   * @param String question
   * Valid argument conditions: 1. neither topic is null 2. both topics are in the graph 3. the
   * question is not in the graph
   */
  public void addQuestion(String topic, QuestionNode question) {
    if (topic != null && question != null) {
      if (graph.containsKey(topic) && graph.containsKey(question)) {
        if (!graph.get(topic).contains(question) || !graph.get(question).contains(topic)) {
          graph.get(topic).add(question);
          questionNum++;
        }
      } else if (!graph.containsKey(topic)) {
        addTopic(topic);
        addQuestion(topic, question);
      }
    }
  }



  /**
   * Returns a Set that contains all the questions
   * 
   */
  public Set<String> getAllTopics() {
    return topicSet = new TreeSet<String>(graph.keySet());
  }

  /**
   * Get all the neighbor (adjacent) topics of a topics
   *
   */
  public ArrayList<QuestionNode> getTopicQuestions(String topic) {
    return graph.get(topic);
  }


  /**
   * Returns the number of questions in this graph.
   */
  public int questionNum() {
    return questionNum;
  }

  /**
   * Returns the number of topics in this graph.
   */
  public int topicNum() {
    return topicNum;
  }
}
