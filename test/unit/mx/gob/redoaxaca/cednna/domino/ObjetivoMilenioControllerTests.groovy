package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(ObjetivoMilenioController)
@Mock(ObjetivoMilenio)
class ObjetivoMilenioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/objetivoMilenio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.objetivoMilenioInstanceList.size() == 0
        assert model.objetivoMilenioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.objetivoMilenioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.objetivoMilenioInstance != null
        assert view == '/objetivoMilenio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/objetivoMilenio/show/1'
        assert controller.flash.message != null
        assert ObjetivoMilenio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/objetivoMilenio/list'

        populateValidParams(params)
        def objetivoMilenio = new ObjetivoMilenio(params)

        assert objetivoMilenio.save() != null

        params.id = objetivoMilenio.id

        def model = controller.show()

        assert model.objetivoMilenioInstance == objetivoMilenio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/objetivoMilenio/list'

        populateValidParams(params)
        def objetivoMilenio = new ObjetivoMilenio(params)

        assert objetivoMilenio.save() != null

        params.id = objetivoMilenio.id

        def model = controller.edit()

        assert model.objetivoMilenioInstance == objetivoMilenio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/objetivoMilenio/list'

        response.reset()

        populateValidParams(params)
        def objetivoMilenio = new ObjetivoMilenio(params)

        assert objetivoMilenio.save() != null

        // test invalid parameters in update
        params.id = objetivoMilenio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/objetivoMilenio/edit"
        assert model.objetivoMilenioInstance != null

        objetivoMilenio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/objetivoMilenio/show/$objetivoMilenio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        objetivoMilenio.clearErrors()

        populateValidParams(params)
        params.id = objetivoMilenio.id
        params.version = -1
        controller.update()

        assert view == "/objetivoMilenio/edit"
        assert model.objetivoMilenioInstance != null
        assert model.objetivoMilenioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/objetivoMilenio/list'

        response.reset()

        populateValidParams(params)
        def objetivoMilenio = new ObjetivoMilenio(params)

        assert objetivoMilenio.save() != null
        assert ObjetivoMilenio.count() == 1

        params.id = objetivoMilenio.id

        controller.delete()

        assert ObjetivoMilenio.count() == 0
        assert ObjetivoMilenio.get(objetivoMilenio.id) == null
        assert response.redirectedUrl == '/objetivoMilenio/list'
    }
}
